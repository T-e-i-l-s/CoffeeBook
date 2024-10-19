package com.mustafin.ebooks.readerFlow.data.source.network.dictionaryApi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface DictionaryService {
    @GET("api.php")
    @Headers("Accept: application/json")
    suspend fun getWordMeaning(
        @Query("search") search: String,
        @Query("language") language: String = "ru",
        @Query("uselang") uselang: String = "ru",
        @Query("format") format: String = "json",
        @Query("action") action: String = "wbsearchentities"
    ): Response<GetWordMeaningResponse>

    data class GetWordMeaningResponse (
        val search: List<SearchResult>
    )

    data class SearchResult (
        val label: String,
        val description: String
    )
}