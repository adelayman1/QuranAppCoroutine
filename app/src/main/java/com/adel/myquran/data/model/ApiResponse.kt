package com.adel.myquran.data.model

data class ApiResponse<T>(var code:Int, var status: String, var data:T)
