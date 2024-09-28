package com.mustafin.ebooks.mainFlow.data.repositories.booksRepository

import com.mustafin.ebooks.core.data.source.local.booksDatabase.BookEntity
import com.mustafin.ebooks.mainFlow.domain.models.ShortBookModel

interface BooksRepository {
    suspend fun getBooks(): List<ShortBookModel>

    suspend fun addBook(book: BookEntity)
}