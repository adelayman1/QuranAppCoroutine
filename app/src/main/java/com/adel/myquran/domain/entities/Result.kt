package com.adel.myquran.domain.entities

sealed class Result<out T> {
    data class Success<out T>(val data:T) : Result<T>()
    data class Error<out T>(val error: ErrorEntity) : Result<T>()
    class Loading <out T> : Result<T>()
}