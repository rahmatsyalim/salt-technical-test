/*
* Created by rahmatsyalim on 2024/01/06
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.util

import com.syalim.salt_technical_test.domain.util.InternetMonitor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class InternetMonitorTest {

    private val internetMonitor = FakeInternetMonitor()

    @Test
    fun isOnline_equals_true_when_connected() = runTest(UnconfinedTestDispatcher()) {
        var isOnline: Boolean? = null
        backgroundScope.launch {
            internetMonitor.isOnline
                .onEach { isOnline = it }
                .collect()
        }
        internetMonitor.setConnected(true)
        assertEquals(true, isOnline)
    }

    @Test
    fun isOnline_equals_false_when_not_connected() = runTest(UnconfinedTestDispatcher()) {
        var isOnline: Boolean? = null
        backgroundScope.launch {
            internetMonitor.isOnline
                .onEach { isOnline = it }
                .collect()
        }
        internetMonitor.setConnected(false)
        assertEquals(false, isOnline)
    }

}

class FakeInternetMonitor : InternetMonitor {

    private val connectivity = MutableStateFlow(true)

    override val isOnline: Flow<Boolean> = connectivity

    fun setConnected(value: Boolean) {
        connectivity.value = value
    }
}