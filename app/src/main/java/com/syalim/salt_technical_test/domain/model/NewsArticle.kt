package com.syalim.salt_technical_test.domain.model

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

data class NewsArticle(
    val author: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String?
)
