package com.mustafin.ebooks.mainFlow.domain

import android.content.Context
import android.net.Uri
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import java.io.InputStream

class PdfReader(private val context: Context) {
    fun extractTextFromPdf(uri: Uri): String {
        // Получаем InputStream pdf файла
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val textBuilder = StringBuilder()

        inputStream?.use { stream ->
            // Загружаем PDF-документ
            val reader = PdfReader(stream)

            // Извлекаем текст из каждой страницы
            for (i in 1..reader.numberOfPages) {
                val text = PdfTextExtractor.getTextFromPage(reader, i)
                textBuilder.append(text).append("\n")
            }

            // Закрываем PdfReader
            reader.close()
        }

        // Возвращаем извлеченный текст
        return textBuilder.toString().trim()
    }
}