package com.example.retrofitapplication.model

data class Result<T>(var status: Status, var data: T? = null, var message: String? = null) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T) = Result(Status.SUCCESS, data)
        fun <T> error(data: T, message: String) = Result(Status.ERROR, data, message)
        fun <T> loading() = Result(Status.LOADING,null)
    }


}