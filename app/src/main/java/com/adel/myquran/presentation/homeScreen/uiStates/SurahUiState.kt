package com.adel.myquran.presentation.homeScreen.uiStates


data class SurahUiState(
    var isLoading: Boolean = true,
    var errorMessage: String? = null,
    var surah: List<SurahItemUiState>? = null
)
