package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.wordMeaningView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.core.domain.enums.ResponseStatus
import com.mustafin.ebooks.readerFlow.data.repositories.dictionaryRepository.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordMeaningViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
): ViewModel() {
    var loadingStatus by mutableStateOf(LoadingStatus.LOADING)
        private set

    var wordMeaning by mutableStateOf<String?>(null)

    fun getWordMeaning(word: String, context: String) {
        viewModelScope.launch {
            loadingStatus = LoadingStatus.LOADING

            val getWordMeaningResult = dictionaryRepository.getWordMeaning(word, context)

            if (getWordMeaningResult.first != ResponseStatus.SUCCESS) {
                loadingStatus = LoadingStatus.ERROR
            } else {
                wordMeaning = getWordMeaningResult.second
                loadingStatus = LoadingStatus.LOADED
            }
        }
    }
}