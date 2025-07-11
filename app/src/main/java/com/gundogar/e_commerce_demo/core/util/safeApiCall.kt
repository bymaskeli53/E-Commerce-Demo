package com.gundogar.e_commerce_demo.core.util

import com.gundogar.e_commerce_demo.data.remote.ApiResult
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResult<T> {
    return try {
        val response = apiCall()
        ApiResult.Success(response)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        ApiResult.Error(
            message = e.localizedMessage ?: "An unexpected error occurred",
            exception = e
        )
    }
}