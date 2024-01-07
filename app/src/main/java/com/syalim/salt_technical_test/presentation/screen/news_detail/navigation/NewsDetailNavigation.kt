/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen.news_detail.navigation

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.syalim.salt_technical_test.presentation.screen.news_detail.NewsDetailScreen
import com.syalim.salt_technical_test.presentation.screen.news_detail.NewsDetailViewModel


const val NEWS_DETAIL_ROUTE = "NEWS_DETAIL_ROUTE"
const val NEWS_DETAIL_URL = "NEWS_DETAIL_URL"

fun NavController.navigateToNewsDetail(
    url: String,
    navOptions: NavOptions? = null
) {
    navigate(
        route = "$NEWS_DETAIL_ROUTE/${Uri.encode(url)}",
        navOptions = navOptions
    )
}

fun NavGraphBuilder.newsDetailScreen(
    onNavigateBack: () -> Unit
) {
    composable(
        route = "$NEWS_DETAIL_ROUTE/{$NEWS_DETAIL_URL}",
        arguments = listOf(
            navArgument(NEWS_DETAIL_URL) { type = NavType.StringType }
        )
    ) {
        val viewModel: NewsDetailViewModel = hiltViewModel()
        val uiState by viewModel.uiuState.collectAsStateWithLifecycle()

        NewsDetailScreen(
            uiState = uiState,
            onSetIsUiLoading = viewModel::setIsUiLoading,
            onNavigateBack = onNavigateBack
        )
    }
}