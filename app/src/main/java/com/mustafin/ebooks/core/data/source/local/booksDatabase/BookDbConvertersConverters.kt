package com.mustafin.ebooks.core.data.source.local.booksDatabase

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class BookDbConvertersConverters {
    @TypeConverter
    fun booksListToString(content: List<String>): String {
        return Json.encodeToString(ListSerializer(String.serializer()), content)
    }

    @TypeConverter
    fun booksListFromString(value: String): List<String> {
        return Json.decodeFromString(ListSerializer(String.serializer()), value)
    }

    @TypeConverter
    fun renderedListToString(content: List<List<String>>): String {
        return Json.encodeToString(ListSerializer(ListSerializer(String.serializer())), content)
    }

    @TypeConverter
    fun renderedListFromString(value: String): List<List<String>> {
        return Json.decodeFromString(ListSerializer(ListSerializer(String.serializer())), value)
    }
}