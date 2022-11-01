package com.adel.myquran.data.sources.remote.responseModels

import com.google.gson.annotations.SerializedName

data class SurahModelResponse(
    @SerializedName("englishName") var name: String?,
    @SerializedName("name") var arabicName: String?,
    @SerializedName("numberOfAyahs") var verseNum: Int?,
    @SerializedName("revelationType") var type: String?
)

fun SurahModelResponse.toSurahModel() =
    com.adel.myquran.domain.models.SurahModel(name, arabicName, verseNum, type)