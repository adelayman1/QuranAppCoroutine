package com.adel.myquran.data.di.module

import com.adel.myquran.data.api.SurahApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class SurahApiModule {
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): SurahApiService {
        return retrofit.create(SurahApiService::class.java)
    }
}