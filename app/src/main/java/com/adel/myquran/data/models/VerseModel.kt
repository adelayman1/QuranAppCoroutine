package com.example.myqurancore.data.models

import com.google.gson.annotations.SerializedName

data class VerseModel(
    var audio: String?,
    var text: String?,
    @SerializedName("numberInSurah") var verseNumber: Int?
)

fun VerseModel.toVerseModel() =
    com.example.myqurancore.domain.models.VerseModel(
        audio = audio,
        verse = text,
        verseNumber = verseNumber
    )