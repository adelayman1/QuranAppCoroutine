package com.example.myqurancore.domain.utils

interface ErrorHandler {
    fun getError(exception: Exception): Exception
}