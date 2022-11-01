package com.example.myqurancore.data.sources.remote.responseModels

import com.example.myqurancore.data.models.VerseModel
import com.example.myqurancore.data.models.toVerseModel
import com.example.myqurancore.domain.models.SurahDetailsModel
import com.google.gson.annotations.SerializedName

data class SurahDetailsResponse(
    @SerializedName("englishName") var name: String?,
    @SerializedName("name") var arabicName: String?,
    @SerializedName("numberOfAyahs") var verseNum: Int?,
    @SerializedName("revelationType") var type: String?,
    @SerializedName("ayahs") var verses: List<VerseModel?>?
)

fun SurahDetailsResponse.toSurahDetailsModel() = SurahDetailsModel(
    surahEnglishName = name,
    surahArabicName = arabicName,
    surahVersesNumber = verseNum,
    surahType = type,
    surahVerses = verses?.map { it?.toVerseModel() }
)