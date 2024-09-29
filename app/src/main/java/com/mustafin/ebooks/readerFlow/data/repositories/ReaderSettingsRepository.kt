package com.mustafin.ebooks.readerFlow.data.repositories

import com.mustafin.ebooks.readerFlow.domain.models.ReaderSettingsModel

interface ReaderSettingsRepository {
    fun getReaderSettings(): ReaderSettingsModel
}