package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mustafin.ebooks.core.data.repositories.booksRepository.BooksRepository
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.readerFlow.data.repositories.readerProgressRepository.ReaderProgressRepository
import com.mustafin.ebooks.readerFlow.data.repositories.readerSettingsRepository.ReaderSettingsRepository
import com.mustafin.ebooks.readerFlow.domain.models.BookModel
import com.mustafin.ebooks.readerFlow.domain.models.ReaderProgressModel
import com.mustafin.ebooks.readerFlow.domain.models.ReaderSettingsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel читалки
@HiltViewModel
class ReaderScreenViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val readerProgressRepository: ReaderProgressRepository,
    private val readerSettingsRepository: ReaderSettingsRepository
) : ViewModel() {
    var loadingStatus by mutableStateOf(LoadingStatus.LOADING)

    // Книга, которая открыта в читалке
    lateinit var book: BookModel

    // Прогресс чтения открытой книги
    lateinit var readerProgress: ReaderProgressModel

    // Настройки читалки
    private var readerSettings by mutableStateOf<ReaderSettingsModel?>(null)

    // Открыто ли меню
    var showMenu by mutableStateOf(false)

    // Функция полной загрузки данных
    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            loadingStatus = LoadingStatus.LOADING
            book = booksRepository.getBookById(1) // TODO: Убрать заглушку
            readerProgress = readerProgressRepository.getProgress(1)
            readerSettings = readerSettingsRepository.getReaderSettings()
            loadingStatus = LoadingStatus.LOADED
        }
    }

    // Сохранить страницы, которые были открыты
    fun saveRenderedPages(readerProgress: ReaderProgressModel) {
        CoroutineScope(Dispatchers.IO).launch {
            readerProgressRepository.setProgress(book.id, readerProgress)
        }
    }

    // Открыто ли окно со значением слова
    var showWordMeaning by mutableStateOf(false)
        private set

    // Слово, значение которого нужно показать
    var selectedWord by mutableStateOf<String?>(null)
        private set

    fun showWordMeaning(word: String) {
        selectedWord = word
        showWordMeaning = true
    }

    fun resetSelection() {
        showWordMeaning = false
        selectedWord = null
    }
}