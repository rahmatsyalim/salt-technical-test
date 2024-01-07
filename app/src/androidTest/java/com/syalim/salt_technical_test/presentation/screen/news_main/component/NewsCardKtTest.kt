package com.syalim.salt_technical_test.presentation.screen.news_main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.syalim.salt_technical_test.domain.model.NewsArticle
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

class NewsCardKtTest {

    private lateinit var article: NewsArticle

    private var clickedUrl: String? = null

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun init() {
        article = NewsArticle(
            author = "author",
            description = "description",
            publishedAt = "publishedAt",
            source = "source",
            title = "title",
            url = "url",
            urlToImage = "urlToImage"
        )
        composeRule.setContent {
            NewsCard(
                modifier = Modifier.fillMaxWidth(),
                article = article,
                onClick = {
                    clickedUrl = it
                }
            )
        }
    }

    @Test
    fun card_is_displayed() {
        composeRule.onNodeWithContentDescription("news card")
            .assertExists()
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun text_contents_correct() {
        composeRule.onNodeWithContentDescription("author text")
            .assertExists()
            .assertIsDisplayed()
        composeRule.onNodeWithContentDescription("date text")
            .assertExists()
            .assertIsDisplayed()
        composeRule.onNodeWithContentDescription("title text")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun pass_correct_url_on_card_click() {
        composeRule.apply {
            onNodeWithContentDescription("news card")
                .assertHasClickAction()
                .performClick()
        }
        assertEquals(clickedUrl, article.url)
    }
}