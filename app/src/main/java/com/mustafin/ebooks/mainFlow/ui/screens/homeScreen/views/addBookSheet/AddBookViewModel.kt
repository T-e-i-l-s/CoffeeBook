package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.addBookSheet

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.mustafin.ebooks.core.data.source.local.booksDatabase.BookEntity
import com.mustafin.ebooks.core.domain.extensions.getFileName
import com.mustafin.ebooks.core.domain.extensions.toByteArray
import com.mustafin.ebooks.core.data.repositories.booksRepository.BooksRepositoryImpl
import com.mustafin.ebooks.mainFlow.domain.PdfReader
import com.mustafin.ebooks.mainFlow.domain.models.AddBookViewStatus
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel для View импорта книги
@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val application: Application,
    private val booksRepository: BooksRepositoryImpl,
    private val pdfReader: PdfReader
) : AndroidViewModel(application) {
    var viewStatus by mutableStateOf(AddBookViewStatus.WAITING)

    // Функция обработки полученного файла
    fun precessData() {
        CoroutineScope(Dispatchers.IO).launch {
            viewStatus = AddBookViewStatus.PROCESSING

            try {
                // Текст книги
                val bookContent = pdfReader.extractTextFromPdf(selectedFileUri!!)
                // Картинка первой страницы
                val previewBitmap = pdfReader.extractPreviewFromPdf(selectedFileUri!!)

                // Добавляем книгу в Room
                booksRepository.addBook(
                    BookEntity(
                        name = selectedFileName!!,
                        preview = previewBitmap.toByteArray(),
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

    var isSelected: Boolean by mutableStateOf(false)
        private set

    fun onFileSelected(uri: Uri) {
        isSelected = true
        selectedFileUri = uri
        selectedFileName = uri.getFileName(application)
    }

    // Метод для сброса состояния
    fun resetState() {
        viewStatus = AddBookViewStatus.WAITING
        selectedFileName = null
        isSelected = false
    }
}