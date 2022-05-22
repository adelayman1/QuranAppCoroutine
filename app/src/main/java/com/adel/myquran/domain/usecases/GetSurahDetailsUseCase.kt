package com.adel.myquran.domain.usecases

import com.adel.myquran.data.models.SurahDetailsModel
import com.adel.myquran.data.repositories.SurahRepositoryImpl
import com.adel.myquran.data.utils.ErrorHandlerImpl
import com.adel.myquran.domain.entities.ErrorEntity
import com.adel.myquran.domain.entities.Result
import javax.inject.Inject


class GetSurahDetailsUseCase @Inject constructor(
        private val repository: SurahRepositoryImpl,
        private val errorHandler: ErrorHandlerImpl
) {

    suspend operator fun invoke(surahNum: Int): Result<SurahDetailsModel> {
        return try {
            val data = repository.getSurahDetails(surahNum)
            if (data.code == 200)
                Result.Success(data.data)
            else
                Result.Error(ErrorEntity.Unknown)
        } catch (e: Throwable) {
            Result.Error(errorHandler.getError(e))
        }
    }
}
