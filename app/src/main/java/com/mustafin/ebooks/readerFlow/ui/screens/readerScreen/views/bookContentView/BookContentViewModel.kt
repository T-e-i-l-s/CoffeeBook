package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mustafin.ebooks.readerFlow.domain.models.BookModel

// ViewModel блока с текстом книги
class BookContentViewModel(private val book: BookModel) : ViewModel() {
    // Индексы слов в массиве, которые сейчас открыты
    private var firstWordIndex by mutableIntStateOf(0)
    private var lastWordIndex by mutableStateOf<Int?>(null)

    // Сеттер для индекса последнего слова на странице в массиве
    fun setLastWordIndex(index: Int) {
        lastWordIndex = index
        generateNextPage()
    }

    // Контент каждой из страниц
    var pages = mutableStateListOf<List<String>>()
        private set
    var firstWordsOfAllPages = mutableStateListOf<Int>()
        private set

    init {
        // При создании ViewModel загружаем страницу
        loadContent()
    }

    // Функция загрузки контента страницы
    private fun loadContent() {
        pages.add(
            book.content.subList(
                firstWordIndex,
                (firstWordIndex + 1000).coerceAtMost(book.content.size)
            )
        )
        firstWordsOfAllPages.add(firstWordIndex)
    }

    // Перелистывание на следующую страницу
    private fun generateNextPage() {
        println(firstWordIndex)
        if (firstWordIndex + lastWordIndex!! < book.content.size - 1) {
            // Устанавливаем новые индексы
            firstWordIndex += lastWordIndex!!
            lastWordIndex = null
            // Обновляем контент на странице
            loadContent()
        }
    }
}