/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.domain.usecase

import androidx.paging.PagingData
import com.syalim.salt_technical_test.domain.model.NewsArticle
import com.syalim.salt_technical_test.domain.repo.NewsRepo
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class GetTopHeadlinesUseCase @Inject constructor(
    private val newsRepo: NewsRepo
) {
    operator fun invoke(size: Int): Flow<PagingData<NewsArticle>> {
        return newsRepo.getTopHeadlines(size)
    }
}