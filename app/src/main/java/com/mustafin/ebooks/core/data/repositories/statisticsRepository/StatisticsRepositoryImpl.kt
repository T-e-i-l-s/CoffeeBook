package com.mustafin.ebooks.core.data.repositories.statisticsRepository

import com.mustafin.ebooks.mainFlow.domain.models.StatisticsModel
import kotlinx.coroutines.delay

class StatisticsRepositoryImpl: StatisticsRepository {
    override suspend fun getStatistics(): StatisticsModel {
        delay(1000)
        return StatisticsModel(
            60,
            10
        )
    }
}