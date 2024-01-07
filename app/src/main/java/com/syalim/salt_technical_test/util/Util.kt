/*
* Created by rahmatsyalim on 2024/01/07
* Copyright 2024 Edufund
*/

package com.syalim.salt_technical_test.util

import android.content.Context
import com.syalim.salt_technical_test.R


fun NetworkException.getMessageText(context: Context): String {
    return when (this) {
        is NetworkException.Timeout -> context.getString(R.string.timeout_message)
        is NetworkException.Unauthorized -> context.getString(R.string.unauthorized_message)
        is NetworkException.BadRequest -> context.getString(R.string.bad_request_message)
        is NetworkException.CouldNotReachServer -> context.getString(R.string.could_not_reach_server_message)
        is NetworkException.TooManyRequests -> context.getString(R.string.too_many_request_message)
        is NetworkException.ServerError -> context.getString(R.string.server_error_message)
        is NetworkException.Unexpected -> message
            ?: context.getString(R.string.unexpected_error_message)
    }
}