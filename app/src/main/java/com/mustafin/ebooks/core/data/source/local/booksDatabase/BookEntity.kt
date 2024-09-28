package com.mustafin.ebooks.core.data.source.local.booksDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.ebooks.mainFlow.domain.models.ShortBookModel

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val content: String
) {
    fun toShortBookModel(): ShortBookModel {
        return ShortBookModel(id, name)
    }
}
