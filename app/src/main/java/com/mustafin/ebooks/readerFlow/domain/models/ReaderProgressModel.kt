package com.mustafin.ebooks.readerFlow.domain.models

data class ReaderProgressModel(
    val rendered: List<List<String>>,
    val lastPageFirstWordIndex: Int
)
