package com.mustafin.ebooks.readerFlow.di

import com.mustafin.ebooks.core.data.source.local.readerProgressDatabase.ReaderProgressDatabase
import com.mustafin.ebooks.readerFlow.data.repositories.dictionaryRepository.DictionaryRepository
import com.mustafin.ebooks.readerFlow.data.repositories.dictionaryRepository.DictionaryRepositoryImpl
import com.mustafin.ebooks.readerFlow.data.repositories.readerProgressRepository.ReaderProgressRepository
import com.mustafin.ebooks.readerFlow.data.repositories.readerProgressRepository.ReaderProgressRepositoryImpl
import com.mustafin.ebooks.readerFlow.data.repositories.readerSettingsRepository.ReaderSettingsRepository
import com.mustafin.ebooks.readerFlow.data.repositories.readerSettingsRepository.ReaderSettingsRepositoryImpl
import com.mustafin.ebooks.readerFlow.data.source.network.dictionaryApi.DictionaryApi
import com.mustafin.ebooks.readerFlow.domain.DICTIONARY_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReaderModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DICTIONARY_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideReaderProgressRepository(readerProgressDatabase: ReaderProgressDatabase): ReaderProgressRepository {
        return ReaderProgressRepositoryImpl(readerProgressDatabase)
    }

    @Provides
    @Singleton
    fun provideReaderSettingsRepository(): ReaderSettingsRepository {
        return ReaderSettingsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(retrofit: Retrofit): DictionaryApi {
        return DictionaryApi(retrofit)
    }

    @Provides
    @Singleton
    fun provideDictionaryRepository(dictionaryApi: DictionaryApi): DictionaryRepository {
        return DictionaryRepositoryImpl(dictionaryApi)
    }
}