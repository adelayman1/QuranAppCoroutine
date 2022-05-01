package com.adel.myquran.data.source

import com.adel.myquran.data.model.ApiResponse
import com.adel.myquran.data.model.SurahDetailsModel
import com.adel.myquran.data.model.SurahModel
import retrofit2.http.GET
import retrofit2.http.Path

interface SurahApiService {
    @GET("surah")
    suspend fun getAllSurah(): ApiResponse<List<SurahModel>>

    @GET("surah/{surahNum}/ar.alafasy")
    suspend fun getSurahDetails(@Path("surahNum") surahNum: Int): ApiResponse<SurahDetailsModel>
}

