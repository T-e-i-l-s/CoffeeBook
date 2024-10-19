package com.mustafin.ebooks.core.data.repositories.statisticsRepository

import com.mustafin.ebooks.mainFlow.domain.models.StatisticsModel

interface StatisticsRepository {
    suspend fun getStatistics(): StatisticsModel
}