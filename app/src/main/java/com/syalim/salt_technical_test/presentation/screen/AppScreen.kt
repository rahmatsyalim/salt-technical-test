/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.syalim.salt_technical_test.R
import com.syalim.salt_technical_test.presentation.navigation.AppNavHost


@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    appState: AppState
) {
    val isNoInternet by appState.isOffline.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AppNavHost(
                modifier = Modifier.weight(1f),
                appState = appState
            )

            AnimatedVisibility(visible = isNoInternet) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp),
                    text = stringResource(id = R.string.no_internet_connection),
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}