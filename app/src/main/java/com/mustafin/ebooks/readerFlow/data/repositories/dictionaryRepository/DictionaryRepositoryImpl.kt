package com.mustafin.ebooks.readerFlow.data.repositories.dictionaryRepository

import com.mustafin.ebooks.core.domain.enums.ResponseStatus
import com.mustafin.ebooks.readerFlow.data.source.network.dictionaryApi.DictionaryApi
import com.mustafin.ebooks.readerFlow.domain.models.WordMeaningModel
import javax.inject.Inject

// Репозиторий для работы с толковым словарем
class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi
): DictionaryRepository {
    override suspend fun getWordMeaning(word: String): Pair<ResponseStatus, WordMeaningModel?> {
        return dictionaryApi.getWordMeaning(word)
    }

    override fun saveWordMeaning(wordMeaning: WordMeaningModel) {
        TODO("Not yet implemented")
    }
}