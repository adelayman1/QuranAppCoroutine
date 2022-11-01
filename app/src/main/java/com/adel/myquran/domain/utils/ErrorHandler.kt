package com.adel.myquran.domain.utils

interface ErrorHandler {
    fun getError(exception: Exception): Exception
}