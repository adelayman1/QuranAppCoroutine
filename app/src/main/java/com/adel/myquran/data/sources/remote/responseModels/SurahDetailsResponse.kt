package com.adel.myquran.data.sources.remote.responseModels

import com.adel.myquran.data.models.VerseModel
import com.adel.myquran.data.models.toVerseModel
import com.adel.myquran.domain.models.SurahDetailsModel
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