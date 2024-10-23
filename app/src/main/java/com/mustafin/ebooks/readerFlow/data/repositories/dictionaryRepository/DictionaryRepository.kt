package com.mustafin.ebooks.readerFlow.data.repositories.dictionaryRepository

import com.mustafin.ebooks.core.domain.enums.ResponseStatus

interface DictionaryRepository {
    suspend fun getWordMeaning(
        word: String,
        context: String
    ): Pair<ResponseStatus, String?>
}