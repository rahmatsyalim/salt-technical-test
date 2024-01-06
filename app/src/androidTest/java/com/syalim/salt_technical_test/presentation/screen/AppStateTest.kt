/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.testing.TestNavHostController
import com.syalim.salt_technical_test.data.util.FakeInternetMonitor
import com.syalim.salt_technical_test.domain.util.InternetMonitor
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class AppStateTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Inject
    lateinit var internetMonitor: InternetMonitor

    private lateinit var appState: AppState

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun app_state_currentDestination_route_matched() = runTest {
        var currentDestinationRoute: String? = null
        composeRule.setContent {
            val context = LocalContext.current
            val navController = remember {
                TestNavHostController(context).apply {
                    navigatorProvider.addNavigator(ComposeNavigator())
                    graph = createGraph(startDestination = "screen_1") {
                        composable("screen_1") { }
                        composable("screen_2") { }
                    }
                }
            }
            appState = rememberAppState(
                navController = navController,
                coroutineScope = backgroundScope,
                internetMonitor = internetMonitor
            )
            currentDestinationRoute = appState.currentDestination?.route
            LaunchedEffect(key1 = Unit) {
                navController.navigate("screen_2")
            }
        }
        assertEquals("screen_2", currentDestinationRoute)
    }

    @Test
    fun appState_isOffline_equals_false_when_internet_monitor_isOnline() = runTest(
        UnconfinedTestDispatcher()
    ) {
        composeRule.setContent {
            val context = LocalContext.current
            appState = AppState(
                navController = NavHostController(context),
                coroutineScope = backgroundScope,
                internetMonitor = internetMonitor
            )
        }
        backgroundScope.launch { appState.isOffline.collect() }
        (internetMonitor as FakeInternetMonitor).setConnected(true)
        assertEquals(false, appState.isOffline.value)
    }

    @Test
    fun appState_isOffline_equals_true_when_internet_monitor_is_not_online() = runTest(
        UnconfinedTestDispatcher()
    ) {
        composeRule.setContent {
            val context = LocalContext.current
            appState = AppState(
                navController = NavHostController(context),
                coroutineScope = backgroundScope,
                internetMonitor = internetMonitor
            )
        }
        backgroundScope.launch { appState.isOffline.collect() }
        (internetMonitor as FakeInternetMonitor).setConnected(false)
        assertEquals(true, appState.isOffline.value)
    }

}