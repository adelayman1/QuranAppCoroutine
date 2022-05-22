package com.adel.myquran.data.models

data class ApiResponse<T>(var code:Int, var status: String, var data:T)
