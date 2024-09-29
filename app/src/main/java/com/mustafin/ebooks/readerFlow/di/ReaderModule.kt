package com.mustafin.ebooks.readerFlow.di

import com.mustafin.ebooks.readerFlow.data.repositories.ReaderSettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReaderModule {
    @Provides
    @Singleton
    fun provideReaderSettingsRepository(): ReaderSettingsRepositoryImpl {
        return ReaderSettingsRepositoryImpl()
    }
}