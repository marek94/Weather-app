package com.example.weatherapp.utils

import retrofit2.HttpException
import java.io.IOException

sealed class ResultWrapper<out T> {
    data class Success<T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int?, val error: String?) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultWrapper.NetworkError
            is HttpException -> {
                val code = throwable.code()
                val errorMessage = throwable.message()
                ResultWrapper.GenericError(code, errorMessage)
            }
            else -> {
                ResultWrapper.GenericError(null, null)
            }
        }
    }
}