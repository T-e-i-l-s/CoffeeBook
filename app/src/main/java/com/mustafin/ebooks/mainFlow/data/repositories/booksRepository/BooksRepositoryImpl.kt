package com.mustafin.ebooks.mainFlow.data.repositories.booksRepository

import com.mustafin.ebooks.mainFlow.domain.models.BookModel

class BooksRepositoryImpl: BooksRepository {
    override suspend fun getBooks(): List<BookModel> {
        return listOf(
            BookModel("Капитанская дочка"),
            BookModel("Евгений Онегин"),
            BookModel("Герой нашего времени"),
            BookModel("Горе от ума")
        )
    }

    override suspend fun getLastBook(): BookModel {
        return BookModel("Капитанская дочка")
    }
}