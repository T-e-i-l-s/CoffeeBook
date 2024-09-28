package com.mustafin.ebooks.mainFlow.data.repositories.booksRepository

import com.mustafin.ebooks.core.data.source.local.booksDatabase.BookEntity
import com.mustafin.ebooks.core.data.source.local.booksDatabase.BooksDatabase
import com.mustafin.ebooks.mainFlow.domain.models.ShortBookModel
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksDatabase: BooksDatabase
): BooksRepository {
    override suspend fun getBooks(): List<ShortBookModel> {
        return booksDatabase.booksDao().getBooks().map { it.toShortBookModel() }
    }

    override suspend fun addBook(book: BookEntity) {
        return booksDatabase.booksDao().addBook(book)
    }
}