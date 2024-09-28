package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.addBookSheet

import android.app.Application
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddBookViewModel(private val application: Application): AndroidViewModel(application) {
    var viewStatus by mutableStateOf(AddBookViewStatus.WAITING)

    var progress by mutableStateOf(0f)
    fun precessData() {
        viewModelScope.launch {
            viewStatus = AddBookViewStatus.PROCESSING
            delay(500)
            progress = 1f
            delay(100)
            viewStatus = AddBookViewStatus.COMPLETED
        }
    }


    var selectedFileName: String? by mutableStateOf(null)
        private set

    var isSelected: Boolean by mutableStateOf(false)
        private set

    fun onFileSelected(uri: Uri) {
        isSelected = true
        selectedFileName = getFileName(uri)
    }

    // Метод для получения названия файла по URI
    private fun getFileName(uri: Uri): String? {
        var fileName: String? = null
        val cursor: Cursor? = application.contentResolver.query(
            uri, null, null, null, null
        )
        cursor?.use {
            if (it.moveToFirst()) {
                fileName = it.getString(it.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
            }
        }
        return fileName
    }
}