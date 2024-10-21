package com.mustafin.ebooks.readerFlow.data.repositories.readerProgressRepository

import com.mustafin.ebooks.readerFlow.domain.models.ReaderProgressModel

interface ReaderProgressRepository {
    suspend fun getProgress(bookId: Int): ReaderProgressModel

    suspend fun setProgress(bookId: Int, readerProgress: ReaderProgressModel)
}