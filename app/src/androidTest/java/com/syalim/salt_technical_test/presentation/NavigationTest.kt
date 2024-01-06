/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.syalim.salt_technical_test.domain.util.InternetMonitor
import com.syalim.salt_technical_test.presentation.screen.AppState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var appState: AppState

    @Before
    fun init() {
        hiltRule.inject()
        appState = composeRule.activity.appState
    }

    @Test
    fun firstScreen_is_news_main() {
        val route = "NEWS_MAIN_ROUTE"
        assertEquals(route, appState.navController.currentDestination?.route)
    }
}