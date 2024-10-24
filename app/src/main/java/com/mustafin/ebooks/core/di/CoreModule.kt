package com.mustafin.ebooks.core.di

import android.content.Context
import androidx.room.Room
import com.mustafin.ebooks.core.data.repositories.booksRepository.BooksRepository
import com.mustafin.ebooks.core.data.repositories.booksRepository.BooksRepositoryImpl
import com.mustafin.ebooks.core.data.source.local.booksDatabase.BooksDatabase
import com.mustafin.ebooks.core.data.source.local.readerProgressDatabase.ReaderProgressDatabase
import com.mustafin.ebooks.core.data.source.network.LargeLanguageModelApi.LLMApi
import com.mustafin.ebooks.core.domain.LLM_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        // Устанавливаем timeout в 30 секунд
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(LLM_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLLMApi(retrofit: Retrofit): LLMApi {
        return LLMApi(retrofit)
    }

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