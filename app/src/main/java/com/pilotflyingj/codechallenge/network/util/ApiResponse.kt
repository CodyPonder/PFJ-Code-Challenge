package com.pilotflyingj.codechallenge.network.util

sealed class ApiResponse<T>(
    data: T? = null,
    errorCode: Int? = null,
    exception: Exception? = null,
    isLoading: Boolean = false
) {
    data class Loading<T>(val isLoading: Boolean) : ApiResponse<T>(isLoading = isLoading)

    data class Success<T>(val data: T) : ApiResponse<T>(data = data)

    data class Error<T>(val errorCode: Int) : ApiResponse<T>(errorCode = errorCode)

    data class Except<T>(val exception: Exception) : ApiResponse<T>(exception = exception)
}
