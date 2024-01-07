package com.syalim.salt_technical_test.data.mapper

import com.syalim.salt_technical_test.data.source.local.entity.NewsArticleEntity
import com.syalim.salt_technical_test.data.source.network.response.NewsArticleResponse
import com.syalim.salt_technical_test.data.source.network.response.NewsArticleSourceResponse
import org.junit.Test

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/
class NewsMapperKtTest {


    @Test
    fun assert_NewsArticleResponse_to_NewsArticleEntity_correct() {
        val response = NewsArticleResponse(
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
        )
        val entity = response.asNewsArticleEntity()
        assert(
            entity.title == response.title
                    && entity.source == response.source?.name
                    && entity.author == response.author
                    && entity.description == response.description
                    && entity.publishedAt == response.publishedAt
                    && entity.url == response.url
                    && entity.urlToImage == response.urlToImage
        )
    }

    @Test
    fun assert_NewsArticleEntity_to_NewsArticle_correct() {
        val entity = NewsArticleEntity(
            title = "title1",
            author = "author",
            description = "description",
            publishedAt = "2024-01-07T08:14:29Z",
            source = "name",
            url = "url",
            urlToImage = null
        )
        val article = entity.asNewsArticle()
        assert(
            entity.title == article.title
                    && entity.source == article.source
                    && entity.author == article.author
                    && entity.description == article.description
                    && entity.publishedAt == article.publishedAt
                    && entity.url == article.url
                    && entity.urlToImage == article.urlToImage
        )
    }
}