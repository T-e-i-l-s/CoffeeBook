package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustafin.ebooks.readerFlow.domain.models.BookModel

@Suppress("UNCHECKED_CAST")
class BookContentViewModelFactory (private val book: BookModel): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BookContentViewModel::class.java)) {
            BookContentViewModel(this.book) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}