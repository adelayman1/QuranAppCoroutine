package com.adel.myquran.data.repositories

import com.adel.myquran.data.api.SurahApiService
import com.adel.myquran.data.models.ApiResponse
import com.adel.myquran.data.models.SurahDetailsModel
import com.adel.myquran.data.models.SurahModel
import com.adel.myquran.domain.repositories.SurahRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurahRepositoryImpl @Inject constructor(private var apiService: SurahApiService) :
        SurahRepository {
    override suspend fun getAllSurah(): ApiResponse<List<SurahModel>> =
            apiService.getAllSurah()

    override suspend fun getSurahDetails(surahNum: Int): ApiResponse<SurahDetailsModel> =
            apiService.getSurahDetails(surahNum)


}


