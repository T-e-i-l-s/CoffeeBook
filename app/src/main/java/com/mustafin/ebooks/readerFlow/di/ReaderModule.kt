package com.mustafin.ebooks.readerFlow.di

import com.mustafin.ebooks.readerFlow.data.repositories.dictionaryRepository.DictionaryRepository
import com.mustafin.ebooks.readerFlow.data.repositories.dictionaryRepository.DictionaryRepositoryImpl
import com.mustafin.ebooks.readerFlow.data.repositories.readerSettingsRepository.ReaderSettingsRepository
import com.mustafin.ebooks.readerFlow.data.repositories.readerSettingsRepository.ReaderSettingsRepositoryImpl
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
    fun provideReaderSettingsRepository(): ReaderSettingsRepository {
        return ReaderSettingsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideDictionaryRepository(): DictionaryRepository {
        return DictionaryRepositoryImpl()
    }
}