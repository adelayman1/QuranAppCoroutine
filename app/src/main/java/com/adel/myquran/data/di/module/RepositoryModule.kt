package com.example.myqurancore.data.di.module

import com.example.myqurancore.data.repositories.SurahRepositoryImpl
import com.example.myqurancore.data.utils.ErrorHandlerImpl
import com.example.myqurancore.domain.repositories.SurahRepository
import com.example.myqurancore.domain.utils.ErrorHandler
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