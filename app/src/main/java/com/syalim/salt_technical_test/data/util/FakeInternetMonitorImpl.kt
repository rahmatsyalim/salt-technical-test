/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.util

import com.syalim.salt_technical_test.domain.util.InternetMonitor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class FakeInternetMonitorImpl @Inject constructor() : InternetMonitor {

    private val connectivity = MutableStateFlow(true)

    override val isOnline: Flow<Boolean> = connectivity

    fun setConnected(value: Boolean) {
        connectivity.value = value
    }
}