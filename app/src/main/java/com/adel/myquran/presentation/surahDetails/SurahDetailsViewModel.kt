package com.adel.myquran.presentation.surahDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adel.myquran.data.model.SurahModel
import com.adel.myquran.data.model.VerseModel
import com.adel.myquran.data.repositories.SurahRepository
import com.adel.myquran.utils.Recourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurahDetailsViewModel @Inject constructor(
    repository: SurahRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var surahDetails: MutableStateFlow<Recourse<SurahModel>> =
        MutableStateFlow(Recourse.loading<SurahModel>())
    var verseList: MutableStateFlow<Recourse<List<VerseModel>>> =
        MutableStateFlow(Recourse.loading<List<VerseModel>>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            surahDetails.emit(Recourse.loading<SurahModel>())
            verseList.emit(Recourse.loading<List<VerseModel>>())
            try {
                repository.getSurahDetails(savedStateHandle["surahNum"] ?: "114").collect {
                    with(it) {
                        if (code == 200) {
                            val surah: SurahModel =
                                SurahModel(data.name, data.arabicName, data.verseNum, data.type)
                            surahDetails.emit(Recourse.success<SurahModel>(surah))
                            verseList.emit(Recourse.success<List<VerseModel>>(data.verses))
                        }else{
                            surahDetails.emit(
                                Recourse.error<SurahModel>(
                                    it.status
                                )
                            )
                        }
                    }
                }

            } catch (e: Exception) {
                surahDetails.emit(
                    Recourse.error<SurahModel>(
                        e.message.toString()
                    )
                )
                verseList.emit(
                    Recourse.error<List<VerseModel>>(
                        e.message.toString()
                    )
                )
            }
        }

    }
}
