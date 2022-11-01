package com.example.myqurancore.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myqurancore.domain.usecases.GetAllSurahUseCase
import com.example.myqurancore.presentation.homeScreen.uiStates.SurahItemUiState
import com.example.myqurancore.presentation.homeScreen.uiStates.SurahUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllSurahUseCase: GetAllSurahUseCase
) : ViewModel() {
    private var _surahUiState = MutableStateFlow(SurahUiState())
    var surahUiState: StateFlow<SurahUiState> = _surahUiState.asStateFlow()

    init {
        refreshSurah(false)
    }

    fun refreshSurah(refresh: Boolean) {
        _surahUiState.update { currentUiState ->
            currentUiState.copy(
                isLoading = true,
                surah = null
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val allSurahResult = getAllSurahUseCase.invoke()
                val surahListUiItems = allSurahResult.map { surah ->
                    SurahItemUiState(
                        surahEnglishName = surah.englishName ?: "",
                        surahArabicName = surah.arabicName ?: "",
                        surahType = surah.type ?: "",
                        versesNumInSurah = surah.verseNum ?: 0
                    )
                }
                _surahUiState.update { currentUiState ->
                    currentUiState.copy(isLoading = false, surah = surahListUiItems)
                }
            } catch (e: Exception) {
                _surahUiState.update { currentUiState ->
                    currentUiState.copy(
                        isLoading = false,
                        surah = null,
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