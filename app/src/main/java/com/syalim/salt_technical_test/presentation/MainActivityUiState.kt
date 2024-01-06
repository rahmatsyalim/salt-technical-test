package com.syalim.salt_technical_test.presentation

/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data object Success : MainActivityUiState
}