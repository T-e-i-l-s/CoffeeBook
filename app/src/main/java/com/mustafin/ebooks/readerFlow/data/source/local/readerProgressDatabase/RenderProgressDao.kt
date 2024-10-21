package com.mustafin.ebooks.readerFlow.data.source.local.readerProgressDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RenderProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setProgress(readerProgress: ReaderProgressEntity)

    @Query("SELECT * FROM reader_progress WHERE bookId = :bookId")
    suspend fun getProgress(bookId: Int): ReaderProgressEntity?
}