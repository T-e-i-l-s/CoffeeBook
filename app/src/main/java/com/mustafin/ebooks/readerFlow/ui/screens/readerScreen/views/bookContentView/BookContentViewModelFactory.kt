package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class BookContentViewModelFactory constructor(private val bookContent: List<String>): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BookContentViewModel::class.java)) {
            BookContentViewModel(this.bookContent) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}