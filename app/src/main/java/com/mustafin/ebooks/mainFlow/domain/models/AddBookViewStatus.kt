package com.mustafin.ebooks.mainFlow.domain.models

enum class AddBookViewStatus(val label: String? = null) {
    WAITING,
    SCANNING("Сканируем книгу"),
    SUMMARIZING("Генерируем пересказ"),
    SAVING("Сохраняем данные"),
    COMPLETED, ERROR
}