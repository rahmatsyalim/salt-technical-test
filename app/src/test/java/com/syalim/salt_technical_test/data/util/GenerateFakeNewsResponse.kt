/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.util

import com.syalim.salt_technical_test.data.source.network.response.NewsArticleResponse
import com.syalim.salt_technical_test.data.source.network.response.NewsArticleSourceResponse
import com.syalim.salt_technical_test.data.source.network.response.NewsListResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.lang.Exception


fun generateFakeSuccessResponse(): Response<NewsListResponse> {
    val body = NewsListResponse(
        status = "ok",
        message = null,
        articles = listOf(
            NewsArticleResponse(
                title = "title1",
                author = "author",
                content = "content",
                description = "description",
                publishedAt = "2024-01-07T08:14:29Z",
                source = NewsArticleSourceResponse(
                    id = null,
                    name = "name"
                ),
                url = "url",
                urlToImage = null
            ),
            NewsArticleResponse(
                title = "title2",
                author = "author",
                content = "content",
                description = "description",
                publishedAt = "2024-01-07T08:14:29Z",
                source = NewsArticleSourceResponse(
                    id = null,
                    name = "name"
                ),
                url = "url",
                urlToImage = null
            ),
            NewsArticleResponse(
                title = "title3",
                author = "author",
                content = "content",
                description = "description",
                publishedAt = "2024-01-07T08:14:29Z",
                source = NewsArticleSourceResponse(
                    id = null,
                    name = "name"
                ),
                url = "url",
                urlToImage = null
            )
        )
    )
    return Response.success(body)
}

fun generateFakeErrorResponse(
    code: Int,
    message: String = "error message"
): Response<NewsListResponse> {
    return Response.error(
        code,
        "{\"status\":\"error\", \"code\":\"code\", \"message\":\"$message\"}"
            .toResponseBody("application/json".toMediaTypeOrNull())
    )
}