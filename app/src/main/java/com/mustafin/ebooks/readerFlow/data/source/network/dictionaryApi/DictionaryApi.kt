package com.mustafin.ebooks.readerFlow.data.source.network.dictionaryApi

import com.mustafin.ebooks.core.domain.enums.ResponseStatus
import retrofit2.Retrofit
import javax.inject.Inject

class DictionaryApi @Inject constructor(retrofit: Retrofit) {
    private val service: DictionaryService by lazy {
        retrofit.create(DictionaryService::class.java)
    }

    // Запрос на получение значения слова из сети
    suspend fun getWordMeaning(
        word: String,
        context: String
    ): Pair<ResponseStatus, String?> {
        return try {
            val response = service.getWordMeaning(
                request = DictionaryService.GetWordMeaningRequestBody(
                    messages = listOf(
                        DictionaryService.RequestMessageModel(
                            "user",
                            "Дай определение слова ${word} в отрывке ${context}. " +
                                    "В ответе пиши только само опрделение. " +
                                    "Не давай четких определений именам собственным. " +
                                    "Определение пиши на русском языке."
                        )
                    )
                )
            )

            println(response)

            if (response.isSuccessful) {
                Pair(
                    ResponseStatus.SUCCESS,
                    response.body()!!.choices[0].message.content
                )
            } else if (response.code() >= 500) {
                Pair(ResponseStatus.NETWORK_ERROR, null)
            } else {
                Pair(ResponseStatus.ERROR, null)
            }
        } catch (e: Exception) {
            println(e)
            Pair(ResponseStatus.ERROR, null)
        }
    }
}