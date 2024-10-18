package com.mustafin.ebooks.readerFlow.data.repositories.readerSettingsRepository

import com.mustafin.ebooks.readerFlow.domain.models.ReaderSettingsModel

interface ReaderSettingsRepository {
    fun getReaderSettings(): ReaderSettingsModel
}