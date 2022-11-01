package com.example.myqurancore.domain.repositories

import com.example.myqurancore.domain.models.SurahDetailsModel
import com.example.myqurancore.domain.models.SurahModel

interface SurahRepository {
    suspend fun getAllSurah(refresh: Boolean = false): List<SurahModel>
    suspend fun getSurahDetails(surahNum: Int): SurahDetailsModel
}