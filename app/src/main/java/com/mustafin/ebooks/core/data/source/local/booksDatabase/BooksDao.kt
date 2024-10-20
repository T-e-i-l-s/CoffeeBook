package com.mustafin.ebooks.core.data.source.local.booksDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBook(book: BookEntity)

    @Query("UPDATE books SET rendered = :rendered WHERE id = :bookId")
    suspend fun updateProgress(bookId: Int, rendered: String)

    @Query("SELECT * FROM books ORDER BY id DESC")
    suspend fun getBooks(): List<BookEntity>

    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun getBookById(bookId: Int): BookEntity?
}