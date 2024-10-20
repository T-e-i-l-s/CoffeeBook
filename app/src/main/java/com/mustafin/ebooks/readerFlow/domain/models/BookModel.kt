package com.mustafin.ebooks.readerFlow.domain.models

// Модель с информацией о книге для читалки
data class BookModel(
    val id: Int,
    val name: String,
    val content: List<String>,
    val rendered: List<List<String>>
)
