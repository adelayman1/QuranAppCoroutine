package com.example.myqurancore.presentation.surahDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myqurancore.domain.usecases.GetSurahDetailsUseCase
import com.example.myqurancore.presentation.surahDetails.uiState.SurahDetailsUiState
import com.example.myqurancore.presentation.surahDetails.uiState.SurahInfoUiState
import com.example.myqurancore.presentation.surahDetails.uiState.VerseItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurahDetailsViewModel @Inject constructor(
    var getSurahDetailsUseCase: GetSurahDetailsUseCase, var savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _surahUiState = MutableStateFlow(SurahDetailsUiState())
    var surahUiState = _surahUiState.asStateFlow()

    init {
        getSurahDetails()
    }

    fun getSurahDetails() {
        val surahNum = savedStateHandle["surahNum"] ?: 1
        _surahUiState.update { currentUiState ->
            currentUiState.copy(
                isLoading = true, surahInfo = null, verses = null
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getSurahDetailsUseCase.invoke(surahNum).apply {
                    val versesListUiItems = surahVerses!!.map { verse ->
                        VerseItemUiState(
                            verseAudio = verse?.audio ?: "",
                            verseNumber = verse?.verseNumber ?: 0,
                            verseText = verse?.verse ?: ""
                        )
                    }
                    _surahUiState.update { currentUiState ->
                        currentUiState.copy(
                            isLoading = false, surahInfo = SurahInfoUiState(
                                surahEnglishName ?: "",
                                surahArabicName ?: "",
                                surahVersesNumber ?: 0,
                                surahType == "Meccan",
                            ), verses = versesListUiItems
                        )
                    }
                }
            } catch (e: Exception) {
                _surahUiState.update { currentUiState ->
                    currentUiState.copy(
                        isLoading = false,
                        surahInfo = null,
                        verses = null,
                        errorMessage = e.message.toString()
                    )
                }
            }
        }
    }

    fun errorMessageShown() {
        _surahUiState.update { currentUiState ->
            currentUiState.copy(errorMessage = null)
        }
    }
}
