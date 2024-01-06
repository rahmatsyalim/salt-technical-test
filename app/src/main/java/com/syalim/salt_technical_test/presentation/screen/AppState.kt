/*
* Created by rahmatsyalim on 2024/01/05
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.syalim.salt_technical_test.domain.util.InternetMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    internetMonitor: InternetMonitor
): AppState = remember(
    navController,
    coroutineScope,
    internetMonitor
) {
    AppState(
        navController = navController,
        coroutineScope = coroutineScope,
        internetMonitor = internetMonitor
    )
}

@Stable
class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    internetMonitor: InternetMonitor
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val isOffline = internetMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )
}