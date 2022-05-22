package com.adel.myquran.domain.entities

sealed class ErrorEntity{
    object Network: ErrorEntity()
    object NotFound: ErrorEntity()
    object AccessDenied: ErrorEntity()
    object ServiceUnAvailable: ErrorEntity()
    object Unknown: ErrorEntity()
}
