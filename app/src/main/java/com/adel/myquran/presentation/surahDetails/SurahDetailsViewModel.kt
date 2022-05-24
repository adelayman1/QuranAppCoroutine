package com.adel.myquran.presentation.surahDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adel.myquran.data.models.SurahModel
import com.adel.myquran.data.models.VerseModel
import com.adel.myquran.domain.entities.Result
import com.adel.myquran.domain.usecases.GetSurahDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurahDetailsViewModel @Inject constructor(
        var useCase: GetSurahDetailsUseCase,
        var savedStateHandle: SavedStateHandle
) : ViewModel() {
    var surahDetails: MutableStateFlow<Result<SurahModel>> =
            MutableStateFlow(Result.Loading<SurahModel>())
    var verseList: MutableStateFlow<Result<List<VerseModel>>> =
            MutableStateFlow(Result.Loading<List<VerseModel>>())

    init {
            getSurahDetails()
    }

    fun getSurahDetails() {
        val surahNum = savedStateHandle["surahNum"] ?: 1
        viewModelScope.launch(Dispatchers.IO) {
            surahDetails.emit(Result.Loading())
            verseList.emit(Result.Loading())
            var result = useCase.invoke(surahNum)
            when (result) {
                is Result.Success -> {
                    with(result.data) {
                        val surah = SurahModel(name, arabicName, verseNum, type)
                        surahDetails.emit(Result.Success(surah))
                        verseList.emit(Result.Success(verses))
                    }
                }

                is Result.Error -> {
                    verseList.emit(Result.Error(result.error))

                }
            }
        }
    }
}
