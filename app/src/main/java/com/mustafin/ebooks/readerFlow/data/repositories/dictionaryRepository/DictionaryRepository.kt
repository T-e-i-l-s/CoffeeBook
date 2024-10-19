package com.mustafin.ebooks.readerFlow.data.repositories.dictionaryRepository

import com.mustafin.ebooks.readerFlow.domain.models.WordMeaningModel

interface DictionaryRepository {
    suspend fun getWordMeaning(word: String): WordMeaningModel
    fun saveWordMeaning(wordMeaning: WordMeaningModel)
}