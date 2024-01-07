package com.syalim.salt_technical_test.data.mapper

import com.syalim.salt_technical_test.data.util.generateFakeErrorResponse
import com.syalim.salt_technical_test.util.NetworkException
import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException
import java.lang.IllegalArgumentException
import java.net.SocketTimeoutException

/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/
class NetworkExceptionMapperKtTest {

    private lateinit var responseError: Throwable

    @Test
    fun assert_that_exception_is_Timeout() {
        val error = SocketTimeoutException()
        responseError = error.asNetworkException()
        assertEquals(NetworkException.Timeout, responseError)
    }

    @Test
    fun assert_that_exception_is_CouldNotReachServer() {
        val error = IOException()
        responseError = error.asNetworkException()
        assertEquals(NetworkException.CouldNotReachServer, responseError)
    }

    @Test
    fun assert_that_exception_is_BadRequest() {
        val response = generateFakeErrorResponse(400)
        val error = HttpException(response)
        responseError = error.asNetworkException()
        assertEquals(NetworkException.BadRequest, responseError)
    }

    @Test
    fun assert_that_exception_is_Unauthorized() {
        val response = generateFakeErrorResponse(401)
        val error = HttpException(response)
        responseError = error.asNetworkException()
        assertEquals(NetworkException.Unauthorized, responseError)
    }

    @Test
    fun assert_that_exception_is_TooManyRequests() {
        val response = generateFakeErrorResponse(429)
        val error = HttpException(response)
        responseError = error.asNetworkException()
        assertEquals(NetworkException.TooManyRequests, responseError)
    }

    @Test
    fun assert_that_exception_is_ServerError() {
        val response = generateFakeErrorResponse(500)
        val error = HttpException(response)
        responseError = error.asNetworkException()
        assertEquals(NetworkException.ServerError, responseError)
    }

    @Test
    fun assert_that_exception_is_Unexpected() {
        val error = IllegalArgumentException()
        responseError = error.asNetworkException()
        assert(responseError is NetworkException.Unexpected)
    }

}