package com.adel.myquran.domain.usecases

import com.adel.myquran.domain.models.SurahModel
import com.adel.myquran.domain.repositories.SurahRepository
import com.adel.myquran.domain.utils.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetAllSurahUseCase @Inject constructor(
    private val surahRepository: SurahRepository,
    private val errorHandler: ErrorHandler,
    var ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(refresh: Boolean = false): List<SurahModel> =
        withContext(ioDispatcher) {
            return@withContext try {
                val getAllSurahResult = surahRepository.getAllSurah(refresh)
                getAllSurahResult.filterNot { surah ->
                    surah.arabicName.isNullOrEmpty()
                }
                return@withContext getAllSurahResult
            } catch (e: Exception) {
                throw errorHandler.getError(e)
            }
        }
}