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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber


@OptIn(ExperimentalPagingApi::class)
class NewsArticleRemoteMediator(
    private val newsDatabase: NewsDatabase,
    private val newsApi: NewsApi,
    private val dispatcherIO: CoroutineDispatcher
) : RemoteMediator<Int, NewsArticleEntity>() {

    private val newsDao = newsDatabase.newsDao
    private val remoteKeyDao = newsDatabase.remoteKeyDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsArticleEntity>
    ): MediatorResult {
        return withContext(dispatcherIO) {
            delay(500)
            try {
                val page = when (loadType) {
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys?.nextPage?.minus(1) ?: 1
                    }

                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)
                        remoteKeys?.prevPage ?: return@withContext MediatorResult.Success(remoteKeys != null)
                    }

                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)
                        remoteKeys?.nextPage ?: return@withContext MediatorResult.Success(remoteKeys != null)
                    }
                }
                val articlesResponse = newsApi.getTopHeadlines(
                    pageSize = state.config.pageSize,
                    page = page
                ).articles
                val endOfPaginationReached = articlesResponse.isEmpty()

                newsDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        remoteKeyDao.clearAll()
                        newsDao.clearAll()
                    }
                    val prevPage = if (page > 1) page - 1 else null
                    val nextPage = if (endOfPaginationReached) null else page + 1
                    val remoteKeys = articlesResponse.map {
                        RemoteKeyEntity(
                            label = it.title,
                            prevPage = prevPage,
                            currentPage = page,
                            nextPage = nextPage
                        )
                    }
                    val articles = articlesResponse.map { it.asNewsArticleEntity() }

                    remoteKeyDao.upsertAll(remoteKeys)
                    newsDao.upsertAll(articles)
                }
                return@withContext MediatorResult.Success(endOfPaginationReached)
            } catch (e: Exception) {
                Timber.e(e)
                return@withContext MediatorResult.Error(e.asNetworkException())
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, NewsArticleEntity>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.title?.let { title ->
                remoteKeyDao.getRemoteKey(title)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, NewsArticleEntity>): RemoteKeyEntity? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { article ->
            remoteKeyDao.getRemoteKey(article.title)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, NewsArticleEntity>): RemoteKeyEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { article ->
            remoteKeyDao.getRemoteKey(article.title)
        }
    }

}