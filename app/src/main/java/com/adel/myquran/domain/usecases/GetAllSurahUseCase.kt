package com.adel.myquran.domain.usecases

import com.adel.myquran.data.models.SurahModel
import com.adel.myquran.data.repositories.SurahRepositoryImpl
import com.adel.myquran.data.utils.ErrorHandlerImpl
import com.adel.myquran.domain.entities.ErrorEntity
import com.adel.myquran.domain.entities.Result
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetAllSurahUseCase @Inject constructor(
        private val repository: SurahRepositoryImpl,
        private val errorHandler: ErrorHandlerImpl
) {
    suspend operator fun invoke(): Result<List<SurahModel>> {
        return try {
            val data = repository.getAllSurah()
            if (data.code == 200) {
                Result.Success(data.data)
            } else
                Result.Error(ErrorEntity.Unknown)
        } catch (e: Throwable) {
            Result.Error(errorHandler.getError(e))
        }
    }

//        .map {
//            Result.Success(it) as Result<ApiResponse<List<SurahModel>>>
//        }.onErrorReturn {
//            Result.Error(errorHandler.getError(it))
//        }

}