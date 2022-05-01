package com.adel.myquran.utils

data class Recourse<T>(var data: T?, var error: String?, var statues: ApiStatus = ApiStatus.LOADING) {
    companion object {
        fun <T> success(data: T): Recourse<T> = Recourse(data = data, error = null,statues = ApiStatus.SUCCESS)
        fun <T> error(error: String): Recourse<T> = Recourse(data = null, error = error,statues = ApiStatus.ERROR)
        fun <T> loading(): Recourse<T> = Recourse(data = null, error = null,statues = ApiStatus.LOADING)
    }
}
