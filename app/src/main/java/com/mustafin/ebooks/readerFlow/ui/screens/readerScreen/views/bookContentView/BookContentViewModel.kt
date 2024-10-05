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
    private var lastWordIndex by mutableStateOf<Int?>(null)

    // Сеттер для индекса последнего слова на странице в массиве
    fun setLastWordIndex(index: Int) {
        println("id: $index")
        lastWordIndex = index
    }

    // Контент страницы
    var currentPageContent by mutableStateOf(emptyList<String>())
        private set

    init {
        // При создании ViewModel загружаем страницу
        loadContent()
    }

    // Функция загрузки контента страницы
    private fun loadContent() {
        currentPageContent = bookContent.subList(
            firstWordIndex,
            bookContent.size
        )
        println(currentPageContent)
    }

    // Стек с индексами первых слов на страницах(для листания назад)
    private val firstWordIndexesStack = Stack<Int>()

    // Перелистывание на следующую страницу
    fun openNextPage() {
        if (lastWordIndex != null && firstWordIndex + lastWordIndex!! < bookContent.size - 1) {
            // Сохраняем старое значение firstWordIndex в стек
            firstWordIndexesStack.push(firstWordIndex)
            // Устанавливаем новые индексы
            firstWordIndex += lastWordIndex!! + 1
            lastWordIndex = null
            // Обновляем контент на странице
            loadContent()
        }
    }

    // Перелистывание на предыдущую страницу
    fun openPreviousPage() {
        if (firstWordIndexesStack.size > 0) {
            // Устанавливаем новые индексы
            lastWordIndex = firstWordIndex - 1
            firstWordIndex = firstWordIndexesStack.pop()
            // Обновляем контент на странице
            loadContent()
        }
    }
}