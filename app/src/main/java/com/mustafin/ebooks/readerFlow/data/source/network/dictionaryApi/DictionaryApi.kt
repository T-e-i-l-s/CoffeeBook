package com.mustafin.ebooks.readerFlow.data.source.network.dictionaryApi

import com.mustafin.ebooks.core.domain.enums.ResponseStatus
import com.mustafin.ebooks.readerFlow.domain.models.WordMeaningModel
import retrofit2.Retrofit
import javax.inject.Inject

class DictionaryApi @Inject constructor(retrofit: Retrofit) {
    private val service: DictionaryService by lazy {
        retrofit.create(DictionaryService::class.java)
    }

    data class GetWordMeaningResult(
        val responseStatus: ResponseStatus,
        val wordMeaning: WordMeaningModel?
    )

    // Запрос на получение значения слова из сети
    suspend fun getWordMeaning(word: String): GetWordMeaningResult {
        return try {
            val response = service.getWordMeaning(word)

            println(response)
            if (response.isSuccessful) {
                GetWordMeaningResult(
                    ResponseStatus.SUCCESS,
                    WordMeaningModel(
                        response.body()!!.search[0].label,
                        response.body()!!.search[0].description
                    )
                )
            } else if (response.code() >= 500) {
                GetWordMeaningResult(ResponseStatus.NETWORK_ERROR, null)
            } else {
                GetWordMeaningResult(ResponseStatus.ERROR, null)
            }
        } catch (e: Exception) {
            println("LOCAL ERROR $e")
            GetWordMeaningResult(ResponseStatus.ERROR, null)
        }
    }
}