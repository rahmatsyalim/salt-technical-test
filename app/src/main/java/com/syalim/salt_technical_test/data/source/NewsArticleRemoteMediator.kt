/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.syalim.salt_technical_test.data.mapper.asNetworkException
import com.syalim.salt_technical_test.data.mapper.asNewsArticleEntity
import com.syalim.salt_technical_test.data.source.local.NewsDatabase
import com.syalim.salt_technical_test.data.source.local.entity.NewsArticleEntity
import com.syalim.salt_technical_test.data.source.local.entity.RemoteKeyEntity
import com.syalim.salt_technical_test.data.source.network.NewsApi
import com.syalim.salt_technical_test.domain.model.NewsArticle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


@OptIn(ExperimentalPagingApi::class)
class NewsArticleRemoteMediator(
    private val newsDatabase: NewsDatabase,
    private val newsApi: NewsApi,
    private val dispatcherIO: CoroutineDispatcher
) : RemoteMediator<Int, NewsArticleEntity>() {

    private val newsDao = newsDatabase.newsDao
    private val remoteKeyDao = newsDatabase.remoteKeyDao
    private val newsArticlesLabel = "news_articles"

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsArticleEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val remoteKey = newsDatabase.withTransaction {
                        remoteKeyDao.getKey(newsArticlesLabel)
                    } ?: return MediatorResult.Success(true)
                    if (remoteKey.nextPage == null) {
                        return MediatorResult.Success(true)
                    }
                    remoteKey.nextPage
                }
            }
            withContext(dispatcherIO) {
                val articlesResponse = newsApi.getTopHeadlines(
                    pageSize = state.config.pageSize,
                    page = page
                ).articles

                newsDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        remoteKeyDao.deleteKey(newsArticlesLabel)
                        newsDao.clearAll()
                    }
                    val nextPage = if (articlesResponse.isEmpty()) {
                        null
                    } else {
                        page + 1
                    }
                    val articleEntities = articlesResponse.map {
                        it.asNewsArticleEntity()
                    }
                    remoteKeyDao.upsertKey(
                        RemoteKeyEntity(
                            label = newsArticlesLabel,
                            nextPage = nextPage
                        )
                    )
                    newsDao.upsertAll(articleEntities)
                }
                MediatorResult.Success(
                    endOfPaginationReached = articlesResponse.isEmpty()
                )
            }
        } catch (e: Exception) {
            MediatorResult.Error(e.asNetworkException())
        }
    }

}