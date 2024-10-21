package com.mustafin.ebooks.core.data.source.local.readerProgressDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ReaderProgressEntity::class], version = 1, exportSchema = false)
@TypeConverters(ReaderProgressDbTypeConverters::class)
abstract class ReaderProgressDatabase: RoomDatabase() {
    abstract fun booksDao(): RenderProgressDao
}