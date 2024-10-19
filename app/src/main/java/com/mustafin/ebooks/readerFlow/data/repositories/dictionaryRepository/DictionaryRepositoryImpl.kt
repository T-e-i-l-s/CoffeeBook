package com.mustafin.ebooks.readerFlow.data.repositories.dictionaryRepository

import com.mustafin.ebooks.readerFlow.data.source.network.dictionaryApi.DictionaryApi
import com.mustafin.ebooks.readerFlow.domain.models.WordMeaningModel
import kotlinx.coroutines.delay
import javax.inject.Inject

// Репозиторий для работы с толковым словарем
class DictionaryRepositoryImpl @Inject constructor(
    val dictionaryApi: DictionaryApi
): DictionaryRepository {
    override suspend fun getWordMeaning(word: String): WordMeaningModel {
        val getWordMeaningResult = dictionaryApi.getWordMeaning(word)
        println(getWordMeaningResult)
        return getWordMeaningResult.wordMeaning!!
    }

    override fun saveWordMeaning(wordMeaning: WordMeaningModel) {
        TODO("Not yet implemented")
    }
}