package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.core.data.repositories.booksRepository.BooksRepositoryImpl
import com.mustafin.ebooks.mainFlow.domain.models.ShortBookModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// View Model главного экрана
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val booksRepository: BooksRepositoryImpl
) : ViewModel() {
    var loadingStatus by mutableStateOf(LoadingStatus.LOADING)
        private set

    var books by mutableStateOf<List<ShortBookModel>>(emptyList())
        private set

    init {
        // При создании View Model загружем данные
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            books = withContext(Dispatchers.IO) {
                booksRepository.getBooks()
            }
            delay(3000)
            loadingStatus = LoadingStatus.LOADED
        }
    }

    // Открыто ли модальное окно импорта книги
    var isAddBookSheetOpened by mutableStateOf(false)
        private set

    fun openAddBookSheet() {
        isAddBookSheetOpened = true
    }

    fun closeAddBookSheet() {
        isAddBookSheetOpened = false
    }
}