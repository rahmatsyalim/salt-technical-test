/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.syalim.salt_technical_test.data.mapper.asNewsArticle
import com.syalim.salt_technical_test.data.source.NewsArticleRemoteMediator
import com.syalim.salt_technical_test.data.source.local.NewsDatabase
import com.syalim.salt_technical_test.data.source.network.NewsApi
import com.syalim.salt_technical_test.domain.model.NewsArticle
import com.syalim.salt_technical_test.domain.repo.NewsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepoImpl(
    private val dispatchersIO: CoroutineDispatcher,
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
) : NewsRepo {

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopHeadlines(size: Int): Flow<PagingData<NewsArticle>> {
        return Pager(
            config = PagingConfig(
                pageSize = size,
                initialLoadSize = size.times(2),
                prefetchDistance = 2
            ),
            remoteMediator = NewsArticleRemoteMediator(
                dispatcherIO = dispatchersIO,
                newsApi = newsApi,
                newsDatabase = newsDatabase
            ),
            pagingSourceFactory = {
                newsDatabase.newsDao.pagingSource()
            }
        ).flow.map { pagingData ->
            pagingData.map { it.asNewsArticle() }
        }
    }
}