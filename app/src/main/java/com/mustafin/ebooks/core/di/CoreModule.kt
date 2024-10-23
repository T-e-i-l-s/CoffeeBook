package com.mustafin.ebooks.core.di

import android.content.Context
import androidx.room.Room
import com.mustafin.ebooks.core.data.repositories.booksRepository.BooksRepository
import com.mustafin.ebooks.core.data.repositories.booksRepository.BooksRepositoryImpl
import com.mustafin.ebooks.core.data.source.local.booksDatabase.BooksDatabase
import com.mustafin.ebooks.core.data.source.local.readerProgressDatabase.ReaderProgressDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideBooksRepository(
        booksDatabase: BooksDatabase,
        readerProgressDatabase: ReaderProgressDatabase
    ): BooksRepository {
        return BooksRepositoryImpl(booksDatabase, readerProgressDatabase)
    }

    @Provides
    @Singleton
    fun provideBooksDatabase(@ApplicationContext context: Context): BooksDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BooksDatabase::class.java,
            "books_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideReaderProgressDatabase(@ApplicationContext context: Context): ReaderProgressDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ReaderProgressDatabase::class.java,
            "reader_progress_database"
        ).build()
    }
}