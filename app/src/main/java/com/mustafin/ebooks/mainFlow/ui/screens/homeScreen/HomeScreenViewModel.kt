package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.mainFlow.data.repositories.booksRepository.BooksRepositoryImpl
import com.mustafin.ebooks.mainFlow.domain.models.BookModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// View Model главного экрана
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val booksRepository: BooksRepositoryImpl
) : ViewModel() {
    var loadingStatus = LoadingStatus.LOADING
        private set

    var books: List<BookModel> = emptyList()
        private set

    init {
        // При создании View Model загружем данные
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            books = booksRepository.getBooks()
            loadingStatus = LoadingStatus.LOADED
        }
    }
}