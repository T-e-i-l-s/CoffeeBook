package com.mustafin.ebooks.core.data.source.local.booksDatabase

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class BookConverters {
    @TypeConverter
    fun fromList(content: List<String>): String {
        return Json.encodeToString(ListSerializer(String.serializer()), content)
    }

    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.decodeFromString(ListSerializer(String.serializer()), value)
    }
}