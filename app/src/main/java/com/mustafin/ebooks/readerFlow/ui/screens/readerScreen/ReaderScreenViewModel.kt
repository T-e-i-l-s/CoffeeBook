package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.ebooks.core.data.repositories.booksRepository.BooksRepositoryImpl
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.readerFlow.data.repositories.ReaderSettingsRepositoryImpl
import com.mustafin.ebooks.readerFlow.domain.models.BookModel
import com.mustafin.ebooks.readerFlow.domain.models.ReaderSettingsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// ViewModel читалки
@HiltViewModel
class ReaderScreenViewModel @Inject constructor(
    private val booksRepositoryImpl: BooksRepositoryImpl,
    private val readerSettingsRepositoryImpl: ReaderSettingsRepositoryImpl
): ViewModel() {
    var loadingStatus by mutableStateOf(LoadingStatus.LOADING)

    // Книга, которая открыта в читалке
    lateinit var book: BookModel

    // Настройки читалки
    var readerSettings by mutableStateOf<ReaderSettingsModel?>(null)

    // Функция полной загрузки данных
    fun loadData() {
        viewModelScope.launch {
            loadingStatus = LoadingStatus.LOADING
            withContext(Dispatchers.IO) {
                book = booksRepositoryImpl.getBookById(1) // TODO: Убрать заглушку
                readerSettings = readerSettingsRepositoryImpl.getReaderSettings()
            }
            loadingStatus = LoadingStatus.LOADED
        }
    }
}