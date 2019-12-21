package com.eventersapp.gojek_trending.util

import com.eventersapp.gojek_trending.domain.useCases.Result
import com.eventersapp.gojek_trending.domain.useCases.ErrorResult
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> safeApiCall(
    call: suspend () -> Result<T>,
    errorMessage: String? = null
): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        when (e) {
            is UnknownHostException ->
                ErrorResult(message ="Please check your internet connection")
            is SocketTimeoutException ->
                ErrorResult(message ="Slow internet connection")
            else ->
                ErrorResult(message =errorMessage ?: "An unknown error occured")
        }
    }
}