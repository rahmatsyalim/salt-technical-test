/*
* Created by rahmatsyalim on 2024/01/05
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.syalim.salt_technical_test.presentation.screen.news_main.navigation.NEWS_MAIN_ROUTE
import com.syalim.salt_technical_test.presentation.screen.news_main.navigation.newsMainScreen
import com.syalim.salt_technical_test.presentation.screen.AppState
import com.syalim.salt_technical_test.presentation.screen.news_detail.navigation.navigateToNewsDetail
import com.syalim.salt_technical_test.presentation.screen.news_detail.navigation.newsDetailScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    appState: AppState,
    startDestination: String = NEWS_MAIN_ROUTE
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        newsMainScreen(
            onNavigateToNewsDetail = navController::navigateToNewsDetail
        )

        newsDetailScreen(
            onNavigateBack = navController::popBackStack
        )
    }

}