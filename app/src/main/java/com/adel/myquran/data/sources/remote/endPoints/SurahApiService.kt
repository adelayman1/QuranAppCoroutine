package com.example.myqurancore.data.sources.remote.endPoints

import com.example.myqurancore.data.sources.remote.responseModels.BaseApiResponse
import com.example.myqurancore.data.sources.remote.responseModels.SurahDetailsResponse
import com.example.myqurancore.data.sources.remote.responseModels.SurahModelResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SurahApiService {
    @GET("surah")
    suspend fun getAllSurah(): BaseApiResponse<List<SurahModelResponse>>

    @GET("surah/{surahNum}/ar.alafasy")
    suspend fun getSurahDetails(@Path("surahNum") surahNum: Int): BaseApiResponse<SurahDetailsResponse>
}