package com.syalim.salt_technical_test.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.syalim.salt_technical_test.domain.util.InternetMonitor
import com.syalim.salt_technical_test.presentation.screen.AppScreen
import com.syalim.salt_technical_test.presentation.screen.AppState
import com.syalim.salt_technical_test.presentation.screen.rememberAppState
import com.syalim.salt_technical_test.presentation.ui.theme.SaltTechnicalTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var internetMonitor: InternetMonitor

    private val viewModel: MainActivityViewModel by viewModels()

    lateinit var appState: AppState

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashscreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.onEach {
                    uiState = it
                }.collect()
            }
        }

        splashscreen.setKeepOnScreenCondition {
            uiState is MainActivityUiState.Loading
        }

        setContent {
            appState = rememberAppState(internetMonitor = internetMonitor)
            SaltTechnicalTestTheme {
                AppScreen(
                    modifier = Modifier.fillMaxSize(),
                    appState = appState
                )
            }
        }
    }
}