/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.mapper

import com.syalim.salt_technical_test.data.source.local.entity.NewsArticleEntity
import com.syalim.salt_technical_test.data.source.network.response.NewsArticleResponse
import com.syalim.salt_technical_test.domain.model.NewsArticle


fun NewsArticleResponse.asNewsArticleEntity(): NewsArticleEntity {
    return NewsArticleEntity(
        author = author,
        description = description,
        publishedAt = publishedAt,
        source = source?.name,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun NewsArticleEntity.asNewsArticle(): NewsArticle {
    return NewsArticle(
        author = author ?: "Unknown",
        description = description ?:"No Description.",
        publishedAt = publishedAt,
        source = source ?: "Unknown",
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}