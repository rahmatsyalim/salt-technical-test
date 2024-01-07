/*
* Created by rahmatsyalim on 2024/01/05
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen.news_main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.syalim.salt_technical_test.presentation.ui.component.CustomScaffold


@Composable
fun NewsMainScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsMainViewModel = hiltViewModel(),
    onNavigateToNewsDetail: (url: String) -> Unit
) {

    CustomScaffold(
        modifier = modifier
    ) {

    }
}