/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data

import com.syalim.salt_technical_test.data.util.FakeInternetMonitorImpl
import com.syalim.salt_technical_test.domain.util.InternetMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
abstract class TestDataModule {

    @Binds
    abstract fun bindsInternetMonitor(
        internetMonitor: FakeInternetMonitorImpl
    ): InternetMonitor
}