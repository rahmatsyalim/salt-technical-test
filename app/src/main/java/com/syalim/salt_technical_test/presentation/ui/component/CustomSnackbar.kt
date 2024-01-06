/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@Composable
fun rememberCustomSnackbarHostState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    snackbarType: MutableState<CustomSnackbarType> = remember {
        mutableStateOf(CustomSnackbarType.Info)
    }
): CustomSnackbarHostState {
    return remember(
        coroutineScope,
        snackbarHostState,
        snackbarType.value
    ) {
        CustomSnackbarHostState(
            coroutineScope = coroutineScope,
            hostState = snackbarHostState,
            type = snackbarType
        )
    }
}

@Composable
fun CustomSnackbarHost(
    modifier: Modifier = Modifier,
    state: CustomSnackbarHostState,
    shape: Shape = RoundedCornerShape(12.dp),
    contentPadding: PaddingValues = PaddingValues(16.dp)
) {
    val type = state.type.value
    val hostState = state.hostState
    SnackbarHost(
        modifier = modifier.testTag("custom_snackbar_host"),
        hostState = hostState
    ) { data ->
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(
                    when (type) {
                        is CustomSnackbarType.Info -> MaterialTheme.colorScheme.surfaceVariant
                        is CustomSnackbarType.Error -> MaterialTheme.colorScheme.errorContainer
                    }
                )
                .padding(contentPadding)
                .testTag("custom_snackbar_host_text"),
            text = data.visuals.message,
            color = when (type) {
                is CustomSnackbarType.Info -> MaterialTheme.colorScheme.onSurfaceVariant
                is CustomSnackbarType.Error -> MaterialTheme.colorScheme.onErrorContainer
            }
        )
    }
}

@Stable
class CustomSnackbarHostState(
    private val coroutineScope: CoroutineScope,
    val hostState: SnackbarHostState,
    val type: MutableState<CustomSnackbarType>
) {

    fun showSnackbar(
        message: String,
        type: CustomSnackbarType = CustomSnackbarType.Info,
        duration: SnackbarDuration = SnackbarDuration.Long,
        onFinished: () -> Unit = {}
    ) {
        coroutineScope.launch {
            this@CustomSnackbarHostState.type.value = type
            hostState.showSnackbar(
                message = message,
                duration = duration
            )
        }.invokeOnCompletion { onFinished() }
    }

    fun hide() {
        hostState.currentSnackbarData?.dismiss()
    }
}

sealed interface CustomSnackbarType {
    data object Info : CustomSnackbarType
    data object Error : CustomSnackbarType
}