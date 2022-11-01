package com.adel.myquran.presentation.surahDetails.uiState

data class SurahInfoUiState(
    var englishName: String,
    var arabicName: String,
    var versesNumber: Int,
    var isMeccen: Boolean = true
)