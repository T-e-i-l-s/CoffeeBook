package com.mustafin.ebooks.core.data.source.local.booksDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: BookEntity)

    @Query("SELECT * FROM books")
    fun getBooks(): List<BookEntity>

    @Query("SELECT * FROM books WHERE id = :userId")
    suspend fun getBookById(userId: Int): BookEntity?
}