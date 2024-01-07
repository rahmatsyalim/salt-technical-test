/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.source.network

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        if (message.startsWith("{") || message.startsWith("[")) {
            Timber.d(message)
        }
    }
}