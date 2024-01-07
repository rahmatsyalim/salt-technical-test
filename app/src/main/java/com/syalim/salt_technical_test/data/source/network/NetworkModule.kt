package com.syalim.salt_technical_test.data.source.network

import com.squareup.moshi.Moshi
import com.syalim.salt_technical_test.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMoshiConverter(): Moshi = Moshi.Builder().build()

    @Provides
    fun providesOkhttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: LoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
                )
                addInterceptor(
                    HttpLoggingInterceptor(loggingInterceptor)
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }
        }
        .build()

    @Provides
    @Singleton
    fun provideNewsApi(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): NewsApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
        .create()

}