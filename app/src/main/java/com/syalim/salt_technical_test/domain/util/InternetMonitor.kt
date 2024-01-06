/*
* Created by rahmatsyalim on 2024/01/05
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.domain.util

import kotlinx.coroutines.flow.Flow

interface InternetMonitor {
    val isOnline: Flow<Boolean>
}