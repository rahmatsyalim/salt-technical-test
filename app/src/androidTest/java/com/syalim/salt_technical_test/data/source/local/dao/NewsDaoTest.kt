/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.source.local.dao

import com.syalim.salt_technical_test.data.source.local.NewsDatabase
import com.syalim.salt_technical_test.data.source.local.entity.NewsArticleEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class NewsDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var newsDatabase: NewsDatabase

    private lateinit var newsDao: NewsDao

    @Before
    fun init() {
        hiltRule.inject()
        newsDao = newsDatabase.newsDao
    }

    @After
    fun cleanUp() {
        newsDatabase.close()
    }

    @Test
    fun getAll_assert_articles_is_correct() = runTest {
        val newsArticles = listOf(1, 2, 3).map {
            testNewsArticleEntity(title = it.toString())
        }
        newsDao.upsertAll(newsArticles)
        val savedNewsArticles = newsDao.getAll()
        assertArrayEquals(savedNewsArticles.toTypedArray(), newsArticles.toTypedArray())
    }

    @Test
    fun upsertAll_assert_articles_is_replaced() = runTest {
        val newsArticles = listOf(1, 2, 3).map {
            testNewsArticleEntity(title = it.toString())
        }
        newsDao.upsertAll(newsArticles)
        val updatedNewsArticles = newsArticles.map {
            testNewsArticleEntity(
                title = it.title,
                description = it.title
            )
        }
        newsDao.upsertAll(updatedNewsArticles)
        val updatedSavedNewsArticles = newsDao.getAll()
        assertArrayEquals(updatedSavedNewsArticles.toTypedArray(), updatedNewsArticles.toTypedArray())
    }

    @Test
    fun clearAll_assert_articles_is_deleted() = runTest {
        var savedNewsArticles: List<NewsArticleEntity>?
        val newsArticles = listOf(1, 2, 3).map {
            testNewsArticleEntity(title = it.toString())
        }
        newsDao.upsertAll(newsArticles)
        savedNewsArticles = newsDao.getAll()
        assertArrayEquals(savedNewsArticles.toTypedArray(), newsArticles.toTypedArray())
        newsDao.clearAll()
        savedNewsArticles = newsDao.getAll()
        assertEquals(0, savedNewsArticles.size)
    }

}

private fun testNewsArticleEntity(
    title: String,
    description: String = ""
) = NewsArticleEntity(
    title = title,
    author = "",
    description = description,
    publishedAt = "",
    source = "",
    url = "",
    urlToImage = ""
)