package com.mustafin.ebooks.readerFlow.data.repositories.readerProgressRepository

import com.mustafin.ebooks.core.data.source.local.readerProgressDatabase.ReaderProgressDatabase
import com.mustafin.ebooks.core.data.source.local.readerProgressDatabase.ReaderProgressEntity
import com.mustafin.ebooks.readerFlow.domain.models.ReaderProgressModel
import javax.inject.Inject

// Репозиторий для работы с книгами
class ReaderProgressRepositoryImpl @Inject constructor(
    private val readerProgressDatabase: ReaderProgressDatabase
): ReaderProgressRepository {
    override suspend fun getProgress(bookId: Int): ReaderProgressModel {
        return readerProgressDatabase.booksDao().getProgress(bookId)
            ?.toReaderProgressModel()
            ?: ReaderProgressModel(emptyList(), 0)
    }

    override suspend fun setProgress(bookId: Int, readerProgress: ReaderProgressModel) {
        readerProgressDatabase.booksDao().setProgress(
            ReaderProgressEntity(
                bookId,
                readerProgress.rendered,
                readerProgress.lastPageFirstWordIndex
            )
        )
    }
}