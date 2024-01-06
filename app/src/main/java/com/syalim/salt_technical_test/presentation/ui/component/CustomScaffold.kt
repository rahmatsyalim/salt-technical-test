/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.syalim.salt_technical_test.presentation.ui.component.pull_refresh.PullRefreshIndicator
import com.syalim.salt_technical_test.presentation.ui.component.pull_refresh.PullRefreshState
import com.syalim.salt_technical_test.presentation.ui.component.pull_refresh.pullRefresh


@Composable
fun CustomScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHostState: CustomSnackbarHostState? = null,
    pullRefreshState: PullRefreshState? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable (innerPadding: PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        containerColor = backgroundColor,
        topBar = topBar,
        bottomBar = bottomBar
    ) { innerPadding ->
        content(innerPadding)
        if (pullRefreshState != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(state = pullRefreshState),
                content = {
                    content(innerPadding)
                    val padding by animateDpAsState(
                        targetValue = if (pullRefreshState.refreshing) innerPadding.calculateTopPadding() + 16.dp
                        else 0.dp,
                        label = "pull_indicator_padding"
                    )
                    PullRefreshIndicator(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = padding),
                        refreshing = pullRefreshState.refreshing,
                        state = pullRefreshState,
                        contentColor = MaterialTheme.colorScheme.primary,
                        backgroundColor = Color.Unspecified,
                        scale = true
                    )
                }
            )
        } else {
            content(innerPadding)
        }
        if (snackbarHostState != null) {
            CustomSnackbarHost(
                modifier = Modifier.padding(16.dp),
                state = snackbarHostState
            )
        }
    }
}