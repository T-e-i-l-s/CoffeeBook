package com.mustafin.ebooks.mainFlow.domain

// Класс для обработки текста
object ContentProcessor {
    // Функция, которая превращает текст книги в массив из слов, знаков и спец символов
    fun separateContent(content: String): List<String> {
        val regex = Regex("""(\w+|[^\w\s]|[\n])""")
        return regex.findAll(content).map { it.value }.toList()
    }
}