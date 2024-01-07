/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.domain.repo

import androidx.paging.PagingData
import com.syalim.salt_technical_test.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    fun getTopHeadlines(size: Int): Flow<PagingData<NewsArticle>>
}