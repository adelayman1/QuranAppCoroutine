package com.adel.myquran.data.model

import com.google.gson.annotations.SerializedName

data class SurahModel(@SerializedName("englishName") var name: String,
                      @SerializedName("name") var arabicName: String,
                      @SerializedName("numberOfAyahs") var verseNum: Int,
                      @SerializedName("revelationType") var type: String)
