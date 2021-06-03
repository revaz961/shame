package com.example.retrofitapplication.model

sealed class Response<out T> {
    data class Success<T>(val data: T?) : Response<T>()
    data class Error<T>(val data: T?, val message: String) : Response<T>()
    data class Loading(val loading: Boolean = false) : Response<Nothing>()
}