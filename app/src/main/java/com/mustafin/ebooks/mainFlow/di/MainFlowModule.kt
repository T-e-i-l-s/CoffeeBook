package com.mustafin.ebooks.mainFlow.di

import com.mustafin.ebooks.mainFlow.data.repositories.booksRepository.BooksRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainFlowModule {
    @Provides
    @Singleton
    fun provideBooksRepository(): BooksRepositoryImpl {
        return BooksRepositoryImpl()
    }
}