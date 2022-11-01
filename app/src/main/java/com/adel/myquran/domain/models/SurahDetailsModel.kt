package com.adel.myquran.domain.models


data class SurahDetailsModel(
    var surahEnglishName: String?,
    var surahArabicName: String?,
    var surahVersesNumber: Int?,
    var surahType: String?,
    var surahVerses: List<VerseModel?>?
)