/*
* Created by rahmatsyalim on 2024/01/05
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.presentation.screen.news_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.syalim.salt_technical_test.domain.usecase.DateFormatterUseCase
import com.syalim.salt_technical_test.domain.usecase.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class NewsMainViewModel @Inject constructor(
    getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val dateFormatterUseCase: DateFormatterUseCase
) : ViewModel() {

    val news = getTopHeadlinesUseCase.invoke(5)
        .map { pagingData ->
            pagingData.map {
                it.copy(
                    publishedAt = dateFormatterUseCase.invoke(it.publishedAt)
                )
            }
        }
        .cachedIn(viewModelScope)
        .distinctUntilChanged()
}