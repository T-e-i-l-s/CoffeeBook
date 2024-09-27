package com.mustafin.ebooks.core.data.source.local.booksDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BooksDatabase: RoomDatabase() {
    abstract fun booksDao(): BooksDao
}