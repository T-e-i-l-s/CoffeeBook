package com.mustafin.ebooks.readerFlow.data.repositories.readerSettingsRepository

import androidx.compose.ui.unit.sp
import com.mustafin.ebooks.readerFlow.domain.models.ReaderSettingsModel

class ReaderSettingsRepositoryImpl: ReaderSettingsRepository {
    override fun getReaderSettings(): ReaderSettingsModel {
        return ReaderSettingsModel(21.sp)
    }
}