/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.data.mapper

import com.syalim.salt_technical_test.util.NetworkException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


fun Exception.asNetworkException(): NetworkException {
    return when (this) {
        is SocketTimeoutException -> NetworkException.Timeout
        is IOException -> NetworkException.CouldNotReachServer
        is HttpException -> {
            val response = response()
            when (response?.code()) {
                400 -> NetworkException.BadRequest
                401 -> NetworkException.Unauthorized
                429 -> NetworkException.TooManyRequests
                500 -> NetworkException.ServerError
                else -> {
                    try {
                        val errorBody = response?.errorBody()
                        val jsonObject = errorBody?.string()?.let {
                            JSONObject(it)
                        }
                        val message = jsonObject?.getString("message")
                        NetworkException.Unexpected(message)
                    } catch (e: Exception) {
                        NetworkException.Unexpected(e.message)
                    }
                }
            }
        }

        else -> NetworkException.Unexpected("Unexpected error occurred.")
    }
}