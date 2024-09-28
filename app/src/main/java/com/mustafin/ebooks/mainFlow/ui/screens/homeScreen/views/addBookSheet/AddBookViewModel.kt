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
import com.mustafin.ebooks.core.data.source.local.booksDatabase.BookEntity
import com.mustafin.ebooks.mainFlow.data.repositories.booksRepository.BooksRepositoryImpl
import com.mustafin.ebooks.mainFlow.domain.PdfReader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val application: Application,
    private val booksRepository: BooksRepositoryImpl,
    private val pdfReader: PdfReader
) : AndroidViewModel(application) {
    var viewStatus by mutableStateOf(AddBookViewStatus.WAITING)

    fun precessData() {
        CoroutineScope(Dispatchers.IO).launch {
            viewStatus = AddBookViewStatus.PROCESSING
            try {
                val bookContent = pdfReader.extractTextFromPdf(selectedFileUri!!)
                booksRepository.addBook(
                    BookEntity(
                        name = selectedFileName!!,
                        content = bookContent
                    )
                )
                viewStatus = AddBookViewStatus.COMPLETED
            } catch (e: Exception) {
                viewStatus = AddBookViewStatus.ERROR
            }
        }
    }

    var selectedFileName: String? by mutableStateOf(null)
        private set

    private var selectedFileUri: Uri? by mutableStateOf(null)
        private set

    var isSelected: Boolean by mutableStateOf(false)
        private set

    fun onFileSelected(uri: Uri) {
        isSelected = true
        selectedFileUri = uri
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

    // Метод для сброса состояния
    fun resetState() {
        viewStatus = AddBookViewStatus.WAITING
        selectedFileName = null
        isSelected = false
    }
}