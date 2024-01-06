package com.syalim.salt_technical_test.presentation.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/
class CustomSnackbarHostTest {

    private lateinit var state: CustomSnackbarHostState

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun snackbar_assert_visibility_correct() {
        composeRule.setContent {
            state = rememberCustomSnackbarHostState()
            CustomSnackbarHost(state = state)
        }

        state.showSnackbar(message = "")
        composeRule.onNode(
            matcher = hasTestTag("custom_snackbar_host"),
            useUnmergedTree = true
        ).assertIsDisplayed()

        state.hide()
        composeRule.onNode(
            matcher = hasTestTag("custom_snackbar_host"),
            useUnmergedTree = true
        ).assertIsNotDisplayed()
    }

    @Test
    fun info_snackbar_text_and_type_correct() {
        composeRule.setContent {
            state = rememberCustomSnackbarHostState()
            CustomSnackbarHost(state = state)
        }
        state.showSnackbar(
            message = "info",
            type = CustomSnackbarType.Info
        )
        composeRule.onNodeWithTag("custom_snackbar_host_text")
            .assertTextEquals("info")
        assertEquals(CustomSnackbarType.Info, state.type.value)
    }

    @Test
    fun error_snackbar_text_and_type_correct() {
        composeRule.setContent {
            state = rememberCustomSnackbarHostState()
            CustomSnackbarHost(state = state)
        }
        state.showSnackbar(
            message = "error",
            type = CustomSnackbarType.Error
        )
        composeRule.onNodeWithTag("custom_snackbar_host_text")
            .assertTextEquals("error")
        assertEquals(CustomSnackbarType.Error, state.type.value)
    }

}