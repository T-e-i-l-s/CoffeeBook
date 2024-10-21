package com.mustafin.ebooks.core.data.source.local.readerProgressDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.ebooks.readerFlow.domain.models.ReaderProgressModel

@Entity(tableName = "reader_progress")
data class ReaderProgressEntity(
    @PrimaryKey(autoGenerate = true) val bookId: Int = 0,
    val renderedPages: List<List<String>>,
    val lastPageFirstWordIndex: Int
) {
    fun toReaderProgressModel(): ReaderProgressModel {
        return ReaderProgressModel(
            renderedPages,
            lastPageFirstWordIndex
        )
    }
}