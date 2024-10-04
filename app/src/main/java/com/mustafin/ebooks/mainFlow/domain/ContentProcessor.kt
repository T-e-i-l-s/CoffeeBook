package com.mustafin.ebooks.mainFlow.domain

import java.util.regex.Pattern

// Класс для обработки текста
object ContentProcessor {
    // Функция, которая превращает текст книги в массив из слов, знаков и спец символов
    fun separateContent(content: String): List<String> {
        val regexString = Pattern.compile("""(\w+|[^\w\s]|[\n])""")

        val matcher = regexString.matcher(content)
        val result = mutableListOf<String>();

        while (matcher.find()) {
            result.add(matcher.group())
        }

        return result
    }
}