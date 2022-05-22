package com.adel.myquran.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adel.myquran.data.models.SurahModel
import com.adel.myquran.domain.entities.Result
import com.adel.myquran.domain.usecases.GetAllSurahUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val useCase: GetAllSurahUseCase
) : ViewModel() {
    var surahList: MutableStateFlow<Result<List<SurahModel>>> =
            MutableStateFlow(Result.Loading<List<SurahModel>>());

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            surahList.emit(Result.Loading())
            var result = useCase.invoke()
            when (result) {
                is Result.Success -> {
                    surahList.emit(Result.Success(result.data))
                }
                is Result.Error -> {
                    surahList.emit(Result.Error(result.error))
                }
            }
        }
    }
}

//try {
//
//    repositoryImpl.getAllSurah().collect { response ->
//        if (response.code == 200) //on success
//            surahList.emit(Recourse.success<List<SurahModel>>(response.data))
//        else //on error
//            surahList.emit(Recourse.error<List<SurahModel>>(response.status + " code:" + response.code))
//    }
//} catch (e: Throwable) {
//    surahList.emit(Recourse.error<List<SurahModel>>(e.message ?: "unknown error"))
//}
