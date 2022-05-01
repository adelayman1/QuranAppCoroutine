package com.adel.myquran.data.repositories

import com.adel.myquran.data.model.ApiResponse
import com.adel.myquran.data.model.SurahDetailsModel
import com.adel.myquran.data.model.SurahModel
import com.adel.myquran.data.source.SurahApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurahRepository @Inject constructor(private var apiService: SurahApiService) {
    suspend fun getAllSurah(): Flow<ApiResponse<List<SurahModel>>> =
        flow { emit(apiService.getAllSurah()) }.flowOn(Dispatchers.IO)

    suspend fun getSurahDetails(surahNum: String): Flow<ApiResponse<SurahDetailsModel>> =
        flow { emit(apiService.getSurahDetails(Integer.parseInt(surahNum))) }
            .flowOn(Dispatchers.IO)

}


