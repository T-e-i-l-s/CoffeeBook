package com.mustafin.ebooks.mainFlow.domain.models

import android.graphics.Bitmap

// Модель с короткой информацией о книге
data class ShortBookModel(
    val id: Int,
    val name: String,
    val preview: Bitmap,
    val progress: Float
)
