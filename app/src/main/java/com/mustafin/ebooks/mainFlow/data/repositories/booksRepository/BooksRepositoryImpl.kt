package com.mustafin.ebooks.mainFlow.data.repositories.booksRepository

import com.mustafin.ebooks.mainFlow.domain.models.BookModel

class BooksRepositoryImpl: BooksRepository {
    override suspend fun getBooks(): List<BookModel> {
        return listOf(
            BookModel("Book 1"),
            BookModel("Book 2"),
            BookModel("Book 3")
        )
    }
}