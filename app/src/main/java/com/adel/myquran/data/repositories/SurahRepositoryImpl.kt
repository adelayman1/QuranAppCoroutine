package com.adel.myquran.data.repositories

import com.adel.myquran.data.sources.remote.dataSource.SurahRemoteDataSource
import com.adel.myquran.data.sources.remote.responseModels.toSurahDetailsModel
import com.adel.myquran.data.sources.remote.responseModels.toSurahModel
import com.adel.myquran.domain.models.SurahDetailsModel
import com.adel.myquran.domain.models.SurahModel
import com.adel.myquran.domain.repositories.SurahRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurahRepositoryImpl @Inject constructor(
    private var surahRemoteDataSource: SurahRemoteDataSource,
    val externalScope: CoroutineScope,
    var ioDispatcher: CoroutineDispatcher
) : SurahRepository {
    private val allSurahMutex = Mutex()
    private var allSurah: List<SurahModel> = emptyList()
    override suspend fun getAllSurah(refresh: Boolean): List<SurahModel> {
        return if (refresh || allSurah.isEmpty()) {
            externalScope.async {
                surahRemoteDataSource.getAllSurah().also {
                    if (it.code == 200 && it.data != null) {
                        allSurahMutex.withLock {
                            allSurah = it.data!!.map {
                                it.toSurahModel()
                            }.toList()
                        }
                    } else throw Exception(it.status)
                }
            }.await()
            return allSurahMutex.withLock { this.allSurah }
        } else allSurahMutex.withLock { this.allSurah }
    }

    override suspend fun getSurahDetails(surahNum: Int): SurahDetailsModel {
        var surahDetails = surahRemoteDataSource.getSurahDetails(surahNum)
        return if (surahDetails.code == 200 && surahDetails.data != null) surahDetails.data!!.toSurahDetailsModel()
        else throw Exception(surahDetails.status)
    }
}


