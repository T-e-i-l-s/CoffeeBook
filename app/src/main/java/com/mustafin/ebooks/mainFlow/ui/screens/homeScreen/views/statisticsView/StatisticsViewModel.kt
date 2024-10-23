package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.statisticsView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.ebooks.core.data.repositories.statisticsRepository.StatisticsRepository
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.mainFlow.domain.models.StatisticsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel вьюшки со статистикой
@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val statisticsRepository: StatisticsRepository
) : ViewModel() {
    var loadingStatus by mutableStateOf(LoadingStatus.LOADING)
        private set

    var statistics by mutableStateOf<StatisticsModel?>(null)
        private set

    init {
        loadStatistics()
    }

    private fun loadStatistics() {
        viewModelScope.launch {
            statistics = statisticsRepository.getStatistics()
            loadingStatus = LoadingStatus.LOADED
        }
    }
}