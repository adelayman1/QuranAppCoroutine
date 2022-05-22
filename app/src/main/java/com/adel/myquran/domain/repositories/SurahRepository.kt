package com.adel.myquran.domain.repositories

import com.adel.myquran.data.models.ApiResponse
import com.adel.myquran.data.models.SurahDetailsModel
import com.adel.myquran.data.models.SurahModel
import kotlinx.coroutines.flow.Flow

interface SurahRepository {
    suspend fun getAllSurah(): ApiResponse<List<SurahModel>>
    suspend fun getSurahDetails(surahNum: Int): ApiResponse<SurahDetailsModel>
}