package com.mustafin.ebooks.readerFlow.data.repositories.dictionaryRepository

import com.mustafin.ebooks.mainFlow.domain.models.WordMeaningModel
import kotlinx.coroutines.delay

// Репозиторий для работы с толковым словарем
class DictionaryRepositoryImpl: DictionaryRepository {
    override suspend fun getWordMeaning(word: String): WordMeaningModel {
        delay(1000)
        return WordMeaningModel(
            word,
            "Тут будет описание слова"
        )
    }

    override fun saveWordMeaning(wordMeaning: WordMeaningModel) {
        TODO("Not yet implemented")
    }
}