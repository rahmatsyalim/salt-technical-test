/*
* Created by rahmatsyalim on 2024/01/05
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen.news_main.navigation

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.syalim.salt_technical_test.presentation.screen.news_main.NewsMainScreen


const val NEWS_MAIN_ROUTE = "NEWS_MAIN_ROUTE"

fun NavGraphBuilder.newsMainScreen(
    onNavigateToNewsDetail: (url: String) -> Unit
) {
    composable(
        route = NEWS_MAIN_ROUTE
    ) {
        NewsMainScreen(
            modifier = Modifier,
            onNavigateToNewsDetail = onNavigateToNewsDetail
        )
    }
}