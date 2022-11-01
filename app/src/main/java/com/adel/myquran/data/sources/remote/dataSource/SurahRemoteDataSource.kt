package com.example.myqurancore.data.sources.remote.dataSource

import com.example.myqurancore.data.sources.remote.endPoints.SurahApiService
import com.example.myqurancore.data.sources.remote.responseModels.BaseApiResponse
import com.example.myqurancore.data.sources.remote.responseModels.SurahDetailsResponse
import com.example.myqurancore.data.sources.remote.responseModels.SurahModelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SurahRemoteDataSource @Inject constructor(var surahApiService: SurahApiService) {
    suspend fun getAllSurah(): BaseApiResponse<List<SurahModelResponse>> =
        withContext(Dispatchers.IO) {
            surahApiService.getAllSurah()
        }

    suspend fun getSurahDetails(surahNum: Int): BaseApiResponse<SurahDetailsResponse> =
        withContext(Dispatchers.IO) {
            surahApiService.getSurahDetails(surahNum)
        }
}