package com.syalim.salt_technical_test.util

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Named
import javax.inject.Singleton

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class]
)
object TestDispatchersModule {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    @Singleton
    fun providesTestDispatcher(): TestDispatcher = UnconfinedTestDispatcher()

    @Provides
    @Named("IO")
    fun providesDispatcherIO(
        testDispatcher: TestDispatcher
    ): CoroutineDispatcher = testDispatcher

    @Provides
    @Named("Default")
    fun providesDispatcherDefault(
        testDispatcher: TestDispatcher
    ): CoroutineDispatcher = testDispatcher
}