package com.syalim.salt_technical_test.data.source.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsArticleSourceResponse(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String
)