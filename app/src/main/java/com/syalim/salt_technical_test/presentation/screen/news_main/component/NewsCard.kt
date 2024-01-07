/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen.news_main.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syalim.salt_technical_test.domain.model.NewsArticle
import com.syalim.salt_technical_test.presentation.ui.component.CustomImage
import com.syalim.salt_technical_test.presentation.ui.component.CustomScaffold
import com.syalim.salt_technical_test.presentation.ui.theme.SaltTechnicalTestTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    article: NewsArticle,
    onClick: (url: String) -> Unit
) {
    Card(
        modifier = modifier.semantics { contentDescription = "news card" },
        shape = MaterialTheme.shapes.large,
        onClick = { onClick(article.url) },
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )
    ) {
        Box {
            CustomImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4 / 3f),
                data = article.urlToImage
            )

            Text(
                modifier = Modifier
                    .semantics { contentDescription = "author text" }
                    .padding(8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                text = article.author,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .semantics { contentDescription = "date text" }
                        .fillMaxWidth(),
                    text = article.publishedAt,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    modifier = Modifier
                        .semantics { contentDescription = "title text" }
                        .fillMaxWidth(),
                    text = article.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 3
                )
            }
        }
    }
}

@Preview()
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsCardPreview() {
    SaltTechnicalTestTheme {
        CustomScaffold {
            NewsCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                article = NewsArticle(
                    author = "author",
                    description = "description\ndescription",
                    publishedAt = "publishedAt",
                    source = "source",
                    title = "title",
                    url = "url",
                    urlToImage = "url"
                ),
                onClick = {

                }
            )
        }
    }
}