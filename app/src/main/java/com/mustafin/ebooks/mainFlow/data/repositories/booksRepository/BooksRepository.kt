package com.mustafin.ebooks.mainFlow.data.repositories.booksRepository

import com.mustafin.ebooks.mainFlow.domain.models.BookModel

interface BooksRepository {
    suspend fun getBooks(): List<BookModel>

    suspend fun getLastBook(): BookModel
}