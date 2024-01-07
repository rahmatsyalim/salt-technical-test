/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen.news_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.syalim.salt_technical_test.presentation.screen.news_detail.navigation.NEWS_DETAIL_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsDetailUiState())
    val uiuState = _uiState.asStateFlow()

    init {
        val url = savedStateHandle.get<String>(NEWS_DETAIL_URL)
        _uiState.update {
            it.copy(url = url)
        }
    }

    fun setIsUiLoading(value: Boolean) {
        _uiState.update { it.copy(isLoading = value) }
    }
}