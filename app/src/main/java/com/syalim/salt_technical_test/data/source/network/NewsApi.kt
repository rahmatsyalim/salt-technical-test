/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.source.network

import com.syalim.salt_technical_test.data.source.network.response.NewsListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines?country=id")
    suspend fun getTopHeadlines(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): NewsListResponse
}