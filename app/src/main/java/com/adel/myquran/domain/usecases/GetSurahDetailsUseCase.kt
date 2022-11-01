package com.adel.myquran.domain.usecases

import com.adel.myquran.data.repositories.SurahRepositoryImpl
import com.adel.myquran.data.utils.ErrorHandlerImpl
import com.adel.myquran.domain.models.SurahDetailsModel
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
