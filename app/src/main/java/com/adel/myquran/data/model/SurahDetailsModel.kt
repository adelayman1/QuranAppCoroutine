package com.adel.myquran.data.model

import com.google.gson.annotations.SerializedName

data class SurahDetailsModel(@SerializedName("englishName") var name: String,
                             @SerializedName("name") var arabicName: String,
                             @SerializedName("numberOfAyahs") var verseNum: Int,
                             @SerializedName("revelationType") var type: String,
                             @SerializedName("ayahs") var verses: List<VerseModel>)
/*
 SurahModel surahDetails;
    List<VerseModel> verses;
 */
