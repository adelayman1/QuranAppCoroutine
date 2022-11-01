package com.adel.myquran.data.sources.remote.responseModels

data class BaseApiResponse<T>(var code: Int, var status: String, var data: T?)