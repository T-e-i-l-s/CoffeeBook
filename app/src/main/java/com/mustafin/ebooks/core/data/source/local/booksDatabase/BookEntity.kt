package com.mustafin.ebooks.core.data.source.local.booksDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val content: String
)
