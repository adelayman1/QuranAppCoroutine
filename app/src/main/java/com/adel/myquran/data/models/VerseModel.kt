package com.adel.myquran.data.models

import com.google.gson.annotations.SerializedName

data class VerseModel(
        var name: String,
        var audio: String,
        var text: String,
        @SerializedName("numberInSurah") var verseNumber: Int)
