package com.mustafin.ebooks.mainFlow.di

import android.content.Context
import com.mustafin.ebooks.mainFlow.data.repositories.booksRepository.BooksRepositoryImpl
import com.mustafin.ebooks.mainFlow.domain.PdfReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun providePdfReader(@ApplicationContext context: Context): PdfReader {
        return PdfReader(context)
    }
}