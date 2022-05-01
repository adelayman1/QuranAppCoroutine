package com.adel.myquran.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adel.myquran.data.model.SurahModel
import com.adel.myquran.data.repositories.SurahRepository
import com.adel.myquran.utils.Recourse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: SurahRepository) : ViewModel() {
    var surahList: MutableStateFlow<Recourse<List<SurahModel>>> =
            MutableStateFlow(Recourse.loading<List<SurahModel>>());

    init {
       loadData()
    }
    fun loadData(){
        viewModelScope.launch(Dispatchers.IO) {
            surahList.emit(Recourse.loading<List<SurahModel>>())
            try {

                repository.getAllSurah().collect { response ->
                    if (response.code == 200) //on success
                        surahList.emit(Recourse.success<List<SurahModel>>(response.data))
                    else //on error
                        surahList.emit(Recourse.error<List<SurahModel>>(response.status + " code:" + response.code))
                }
            } catch (e: Throwable) {
                surahList.emit(Recourse.error<List<SurahModel>>(e.message ?: "unknown error"))
            }
        }
    }
}
