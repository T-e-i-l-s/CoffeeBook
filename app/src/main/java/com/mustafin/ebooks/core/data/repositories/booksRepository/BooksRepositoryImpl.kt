package com.mustafin.ebooks.core.data.repositories.booksRepository

import com.mustafin.ebooks.core.data.source.local.booksDatabase.BookEntity
import com.mustafin.ebooks.core.data.source.local.booksDatabase.BooksDatabase
import com.mustafin.ebooks.mainFlow.domain.models.ShortBookModel
import com.mustafin.ebooks.readerFlow.domain.models.BookModel
import javax.inject.Inject

// Репозиторий для работы с книгами
class BooksRepositoryImpl @Inject constructor(
    private val booksDatabase: BooksDatabase
): BooksRepository {
    override suspend fun getBooks(): List<ShortBookModel> {
        return booksDatabase.booksDao().getBooks().map { it.toShortBookModel() }
    }

    override suspend fun getBookById(bookId: Int): BookModel {
        return booksDatabase.booksDao().getBookById(bookId)!!.toBookModel()
    }

    override suspend fun addBook(book: BookEntity) {
        return booksDatabase.booksDao().addBook(book)
    }
}