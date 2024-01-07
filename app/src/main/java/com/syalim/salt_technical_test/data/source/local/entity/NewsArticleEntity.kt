package com.syalim.salt_technical_test.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/
@Entity(tableName = "news_articles")
data class NewsArticleEntity(
    val author: String?,
    val description: String?,
    val publishedAt: String,
    val source: String?,
    @PrimaryKey
    val title: String,
    val url: String,
    val urlToImage: String?
)
