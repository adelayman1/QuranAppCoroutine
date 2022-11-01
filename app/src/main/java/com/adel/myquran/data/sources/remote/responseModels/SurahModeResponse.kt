package com.example.myqurancore.data.sources.remote.responseModels

import com.google.gson.annotations.SerializedName

data class SurahModelResponse(
    @SerializedName("englishName") var name: String?,
    @SerializedName("name") var arabicName: String?,
    @SerializedName("numberOfAyahs") var verseNum: Int?,
    @SerializedName("revelationType") var type: String?
)

fun SurahModelResponse.toSurahModel() =
    com.example.myqurancore.domain.models.SurahModel(name, arabicName, verseNum, type)