package com.syalim.salt_technical_test.data

import com.syalim.salt_technical_test.domain.util.InternetMonitor
import com.syalim.salt_technical_test.data.util.InternetMonitorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
* Created by rahmatsyalim on 2024/01/05
* Copyright 2024 Edufund
*/

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsInternetMonitor(
        internetMonitor: InternetMonitorImpl
    ): InternetMonitor
}