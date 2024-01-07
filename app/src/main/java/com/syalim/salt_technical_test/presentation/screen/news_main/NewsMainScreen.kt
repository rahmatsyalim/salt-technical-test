/*
* Created by rahmatsyalim on 2024/01/05
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen.news_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.syalim.salt_technical_test.R
import com.syalim.salt_technical_test.domain.model.NewsArticle
import com.syalim.salt_technical_test.presentation.screen.news_main.component.NewsCard
import com.syalim.salt_technical_test.presentation.ui.component.CustomScaffold
import com.syalim.salt_technical_test.presentation.ui.component.CustomSnackbarType
import com.syalim.salt_technical_test.presentation.ui.component.pull_refresh.rememberPullRefreshState
import com.syalim.salt_technical_test.presentation.ui.component.rememberCustomSnackbarHostState
import com.syalim.salt_technical_test.util.NetworkException
import com.syalim.salt_technical_test.util.getMessageText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsMainScreen(
    modifier: Modifier = Modifier,
    news: LazyPagingItems<NewsArticle>,
    onNavigateToNewsDetail: (url: String) -> Unit
) {

    val context = LocalContext.current
    val snackbarHostState = rememberCustomSnackbarHostState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = news.loadState.refresh is LoadState.Loading,
        onRefresh = news::refresh
    )
    val appendState by remember {
        derivedStateOf { news.loadState.append }
    }
    val refreshState by remember {
        derivedStateOf { news.loadState.refresh }
    }

    CustomScaffold(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        pullRefreshState = pullRefreshState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.top_headlines),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(news.itemCount) { index ->
                news[index]?.let { article ->
                    NewsCard(
                        modifier = Modifier.fillMaxWidth(),
                        article = article,
                        onClick = onNavigateToNewsDetail
                    )
                }
            }

            // bottom append loading content
            if (appendState is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 4.dp
                        )
                    }
                }
            }
            if (appendState is LoadState.Error) {
                // TODO: retry loading data
            }
            // no more data to load
            if (appendState.endOfPaginationReached) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = "No more news.",
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = refreshState) {
        val loadState = refreshState
        if (loadState is LoadState.Error) {
            snackbarHostState.showSnackbar(
                type = CustomSnackbarType.Error,
                message = when (val error = loadState.error) {
                    is NetworkException -> error.getMessageText(context)
                    else -> context.getString(R.string.unexpected_error_message)
                }
            )
        }
    }
}