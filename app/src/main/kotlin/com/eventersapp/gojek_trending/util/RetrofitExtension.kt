package com.eventersapp.gojek_trending.util

import com.eventersapp.gojek_trending.domain.baseUseCase.Result
import com.eventersapp.gojek_trending.domain.baseUseCase.ErrorResult
import com.eventersapp.gojek_trending.domain.baseUseCase.Success
import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw HttpException(this)
    return body()!!
}

fun <T> Response<T>.isFromNetwork(): Boolean {
    return raw().cacheResponse == null
}

fun <T> Response<T>.isFromCache(): Boolean {
    return raw().cacheResponse != null
}

@Suppress("REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE")
suspend fun <T> Response<T>.toResultUnit(): Result<Unit> = toResult { Unit }

@Suppress("REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE")
suspend fun <T> Response<T>.toResult(): Result<T> = toResult { it }

@Suppress("REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE")
suspend fun <T, E> Response<T>.toResult(mapper: suspend (T) -> E): Result<E> {
    if (isSuccessful) {
        return Success(
            data = mapper(bodyOrThrow()),
            responseModified = isFromNetwork()
        )
    } else {
        return ErrorResult(
            message = "An Unknown error occured."
        )
    }
}
