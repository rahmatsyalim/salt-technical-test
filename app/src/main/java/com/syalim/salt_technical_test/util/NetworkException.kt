/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.util

sealed class NetworkException(message: String? = null) : Exception(message) {

    data object Unauthorized : NetworkException()

    data object BadRequest : NetworkException()

    data object CouldNotReachServer : NetworkException()

    data object Timeout : NetworkException()

    data object TooManyRequests : NetworkException()

    data object ServerError : NetworkException()

    class Unexpected(message: String?) : NetworkException(message)
}