package com.adel.myquran.domain.utils

import com.adel.myquran.domain.entities.ErrorEntity

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}