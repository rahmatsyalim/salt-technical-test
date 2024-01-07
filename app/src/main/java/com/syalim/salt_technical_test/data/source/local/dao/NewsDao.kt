/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.syalim.salt_technical_test.data.source.local.entity.NewsArticleEntity

@Dao
interface NewsDao {

    @Upsert
    suspend fun upsertAll(news: List<NewsArticleEntity>)

    @Query("SELECT * FROM news_articles")
    fun pagingSource(): PagingSource<Int, NewsArticleEntity>

    @Query("SELECT * FROM news_articles")
    fun getAll(): List<NewsArticleEntity>

    @Query("DELETE FROM news_articles")
    suspend fun clearAll()

}