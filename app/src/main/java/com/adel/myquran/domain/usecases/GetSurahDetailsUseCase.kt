package com.example.myqurancore.domain.usecases

import com.example.myqurancore.data.repositories.SurahRepositoryImpl
import com.example.myqurancore.data.utils.ErrorHandlerImpl
import com.example.myqurancore.domain.models.SurahDetailsModel
import javax.inject.Inject


class GetSurahDetailsUseCase @Inject constructor(
    private val repository: SurahRepositoryImpl,
    private val errorHandler: ErrorHandlerImpl
) {
    suspend operator fun invoke(surahNum: Int): SurahDetailsModel {
        return try {
            val surahDetails = repository.getSurahDetails(surahNum)
            surahDetails.apply {
                this.surahVerses!!.forEach { verse ->
                    if (verse!!.audio!!.trim().isEmpty()) throw Exception("unknown audio link")
                }
            }
        } catch (e: Exception) {
            throw errorHandler.getError(e)
        }
    }
}
