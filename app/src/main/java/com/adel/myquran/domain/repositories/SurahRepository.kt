package com.adel.myquran.domain.repositories

import com.adel.myquran.domain.models.SurahDetailsModel
import com.adel.myquran.domain.models.SurahModel

interface SurahRepository {
    suspend fun getAllSurah(refresh: Boolean = false): List<SurahModel>
    suspend fun getSurahDetails(surahNum: Int): SurahDetailsModel
}