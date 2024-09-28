package com.mustafin.ebooks.mainFlow.di

import android.content.Context
import androidx.room.Room
import com.mustafin.ebooks.core.data.source.local.booksDatabase.BooksDatabase
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
    fun provideBooksRepository(booksDatabase: BooksDatabase): BooksRepositoryImpl {
        return BooksRepositoryImpl(booksDatabase)
    }

    @Provides
    @Singleton
    fun provideBooksDatabase(@ApplicationContext context: Context): BooksDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BooksDatabase::class.java,
            "books_database" // Имя базы данных
        ).build()
    }

    @Provides
    @Singleton
    fun providePdfReader(@ApplicationContext context: Context): PdfReader {
        return PdfReader(context)
    }
}