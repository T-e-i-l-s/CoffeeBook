package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.wordMeaningView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WordMeaningViewModel: ViewModel() {
    val word = "Привет" // TODO: Перенести в Factory

    var loadingStatus by mutableStateOf(LoadingStatus.LOADING)
        private set

    // Значение слова
    var meaning by mutableStateOf<String?>(null)
        private set

    init {
        findMeaning()
    }

    private fun findMeaning() {
        viewModelScope.launch {
            delay(1000)
            meaning = "Обращённое к кому-н. выражение чувства личной приязни, доброго пожелания, солидарности и т. п."
            loadingStatus = LoadingStatus.LOADED
        }
    }
}