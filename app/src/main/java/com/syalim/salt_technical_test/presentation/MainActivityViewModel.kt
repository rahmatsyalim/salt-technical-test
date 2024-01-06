/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<MainActivityUiState> =
        MutableStateFlow(MainActivityUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            // 1 sec delay to mimic fetching some data during splashscreen
            delay(1_000)
            _uiState.update {
                MainActivityUiState.Success
            }
        }
    }
}