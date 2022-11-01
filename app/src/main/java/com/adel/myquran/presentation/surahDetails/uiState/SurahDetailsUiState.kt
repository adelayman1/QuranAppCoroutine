package com.example.myqurancore.presentation.surahDetails.uiState

data class SurahDetailsUiState(
    var isLoading: Boolean = true,
    var errorMessage: String? = null,
    var surahInfo: SurahInfoUiState? = null,
    var verses: List<VerseItemUiState>? = null
)
