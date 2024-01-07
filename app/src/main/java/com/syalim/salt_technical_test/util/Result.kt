/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.util

sealed interface Result<out T> {
    class Success<T>(val data: T) : Result<T>

    class Error<T>(val data: T?, val exception: Exception?) : Result<T>
}