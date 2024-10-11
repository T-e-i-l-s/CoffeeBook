package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// ViewModel блока с текстом книги
class BookContentViewModel(private val bookContent: List<String>) : ViewModel() {
    // Индексы слов в массиве, которые сейчас открыты
    var firstWordIndex by mutableIntStateOf(0)
    private var lastWordIndex by mutableStateOf<Int?>(null)

    // Сеттер для индекса последнего слова на странице в массиве
    fun setLastWordIndex(index: Int) {
        lastWordIndex = index
        generateNextPage()
    }

    // Контент каждой из страниц
    var pages = mutableStateListOf<List<String>>()
        private set

    init {
        // При создании ViewModel загружаем страницу
        loadContent()
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
    private fun generateNextPage() {
        if (firstWordIndex + lastWordIndex!! < bookContent.size - 1) {
            // Устанавливаем новые индексы
            firstWordIndex += lastWordIndex!!
            lastWordIndex = null
            // Обновляем контент на странице
            loadContent()
        }
    }

    var isRestoring by mutableStateOf(false)

    // Индекс открытой страницы(нужен при повторной загрузке
    var currentPageIndex by mutableIntStateOf(0)

    var restoreTo by mutableIntStateOf(0)

    fun openPageWithWord(wordIndex: Int) {
        if (wordIndex <= firstWordIndex) {
            return
        }
        println("Restore1")
        restoreTo = wordIndex
        isRestoring = true
        // Сбрасываем отображение текста
        currentPageIndex = 0
        firstWordIndex = 0
        lastWordIndex = null
        pages.clear()
        // Рендерим страницы до нужной нам
        loadContent()
    }

    // Сеттер для индекса последнего слова на странице в массиве
    fun restoreLastWordIndex(index: Int) {
        println("Restore2")
        lastWordIndex = index
        if (index > restoreTo) {
            currentPageIndex++
        } else {
            isRestoring = false
        }
        generateNextPage()
    }
}