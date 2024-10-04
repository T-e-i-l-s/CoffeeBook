package com.mustafin.ebooks.core.data.source.local.booksDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mustafin.ebooks.core.domain.extensions.toBitmap
import com.mustafin.ebooks.mainFlow.domain.models.ShortBookModel
import com.mustafin.ebooks.readerFlow.domain.models.BookModel

@Entity(tableName = "books")
@TypeConverters(BookConverters::class)
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val preview: ByteArray,
    val content: List<String>,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookEntity

        if (id != other.id) return false
        if (name != other.name) return false
        if (!preview.contentEquals(other.preview)) return false
        if (content != other.content) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + preview.contentHashCode()
        result = 31 * result + content.hashCode()
        return result
    }
}
