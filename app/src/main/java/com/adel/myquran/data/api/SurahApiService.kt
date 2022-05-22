package com.adel.myquran.data.api

import com.adel.myquran.data.models.ApiResponse
import com.adel.myquran.data.models.SurahDetailsModel
import com.adel.myquran.data.models.SurahModel
import retrofit2.http.GET
import retrofit2.http.Path

interface SurahApiService {
    @GET("surah")
    suspend fun getAllSurah(): ApiResponse<List<SurahModel>>

    @GET("surah/{surahNum}/ar.alafasy")
    suspend fun getSurahDetails(@Path("surahNum") surahNum: Int): ApiResponse<SurahDetailsModel>
}