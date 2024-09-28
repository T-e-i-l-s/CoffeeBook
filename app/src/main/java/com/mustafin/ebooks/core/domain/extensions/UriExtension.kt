package com.mustafin.ebooks.core.domain.extensions

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns

fun Uri.getFileName(context: Context): String? {
    var fileName: String? = null
    val cursor: Cursor? = context.contentResolver.query(
        this, null, null, null, null
    )
    cursor?.use {
        if (it.moveToFirst()) {
            fileName = it.getString(it.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
        }
    }
    return fileName
}