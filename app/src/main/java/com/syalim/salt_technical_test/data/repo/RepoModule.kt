package com.syalim.salt_technical_test.data.repo

import com.syalim.salt_technical_test.data.source.local.NewsDatabase
import com.syalim.salt_technical_test.data.source.network.NewsApi
import com.syalim.salt_technical_test.domain.repo.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideNewsRepo(
        @Named("IO") dispatchersIO: CoroutineDispatcher,
        newsApi: NewsApi,
        newsDatabase: NewsDatabase
    ): NewsRepo = NewsRepoImpl(
        dispatchersIO = dispatchersIO,
        newsApi = newsApi,
        newsDatabase = newsDatabase
    )
}