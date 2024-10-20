package com.mustafin.ebooks.mainFlow.domain.models

// Модель хранения данных статистики чтения
data class StatisticsModel(
    val summaryReadingTime: Int,
    val readingTimeToday: Int,
)
