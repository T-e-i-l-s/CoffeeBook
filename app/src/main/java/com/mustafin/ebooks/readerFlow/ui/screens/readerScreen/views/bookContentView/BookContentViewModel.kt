package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Stack

// ViewModel блока с текстом книги
class BookContentViewModel(private val bookContent: List<String>) : ViewModel() {
    // Индексы слов в массиве, которые сейчас открыты
    private var firstWordIndex by mutableIntStateOf(0)
    private var lastWordIndex by mutableStateOf<Int?>(10)

    // Сеттер для индекса последнего слова на странице в массиве
    fun setLastWordIndex(index: Int) {
        println("Last Word Index: $index")
        lastWordIndex = index
        generateNextPage()
    }

    // Контент каждой из страниц
    var pages = mutableListOf<List<String>>()
        private set

    init {
        // При создании ViewModel загружаем страницу
        loadContent()
        generateNextPage()
    }

    // Функция загрузки контента страницы
    private fun loadContent() {
        pages.add(
            bookContent.subList(
                firstWordIndex,
                bookContent.size
            )
        )
    }

    // Перелистывание на следующую страницу
    fun generateNextPage() {
        if (firstWordIndex + lastWordIndex!! < bookContent.size - 1) {
            // Устанавливаем новые индексы
            firstWordIndex += lastWordIndex!! + 1
            lastWordIndex = 10
            // Обновляем контент на странице
            loadContent()
            generateNextPage()
        }
    }
}