package com.syalim.salt_technical_test.data.source.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsListResponse(
    @Json(name = "articles")
    val articles: List<NewsArticleResponse>,
    @Json(name = "status")
    val status: String,
    @Json(name = "message")
    val message: String?
)