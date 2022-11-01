package com.example.myqurancore.data.utils

import com.example.myqurancore.domain.utils.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    override fun getError(exception: Exception): Exception {
        return when (exception) {
            is IOException -> Exception("No internet connection")
            is UnknownHostException -> Exception("No internet connection")
            is HttpException -> {
                when (exception.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> Exception("unknown error")
                    HttpURLConnection.HTTP_FORBIDDEN -> Exception("Access Denied")
                    HttpURLConnection.HTTP_UNAVAILABLE -> Exception("Service Unavailable")
                    else -> Exception("unknown details")
                }
            }

            else -> exception
        }
    }
}