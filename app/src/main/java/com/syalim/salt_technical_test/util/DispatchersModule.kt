package com.syalim.salt_technical_test.util

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Named("IO")
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named("Default")
    fun providesDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default
}