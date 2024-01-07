/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen.news_main.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syalim.salt_technical_test.domain.model.NewsArticle
import com.syalim.salt_technical_test.presentation.ui.component.CustomImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    article: NewsArticle,
    onClick: (url: String) -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = {}
    ) {
        CustomImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f),
            data = article.urlToImage,
            loadingContent = {
                // TODO: loading placeholder
            },
            errorContent = {
                // TODO: placeholder
            }
        )
    }
}