package com.mustafin.ebooks.core.data.source.local.booksDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mustafin.ebooks.core.domain.extensions.toBitmap
import com.mustafin.ebooks.mainFlow.domain.models.ShortBookModel
import com.mustafin.ebooks.readerFlow.domain.models.BookModel

@Entity(tableName = "books")
@TypeConverters(BookDbConvertersConverters::class)
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val preview: ByteArray,
    val content: List<String>
) {
    fun toShortBookModel(): ShortBookModel {
        return ShortBookModel(
            id,
            name,
            preview.toBitmap(),
        )
    }

    fun toBookModel(): BookModel {
        return BookModel(
            id,
            name,
            content
        )
    }
}
