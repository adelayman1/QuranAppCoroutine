package com.adel.myquran.data.di.module

import com.adel.myquran.data.repositories.SurahRepositoryImpl
import com.adel.myquran.data.utils.ErrorHandlerImpl
import com.adel.myquran.domain.repositories.SurahRepository
import com.adel.myquran.domain.utils.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideSurahRepository(surahRepository: SurahRepositoryImpl): SurahRepository {
        return surahRepository
    }

    @Singleton
    @Provides
    fun provideErrorHandler(errorHandler: ErrorHandlerImpl): ErrorHandler {
        return errorHandler
    }
}