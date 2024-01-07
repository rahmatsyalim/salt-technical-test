/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.source.network

import com.syalim.salt_technical_test.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = BuildConfig.API_KEY
        return chain.getResponse(apiKey)
    }

    private fun Interceptor.Chain.getResponse(apiKey: String): Response {
        return proceed(
            request = request().newBuilder()
                .addHeader("Authorization", apiKey)
                .build()
        )
    }
}