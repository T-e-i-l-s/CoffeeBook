package com.mustafin.ebooks.readerFlow.data.source.local.readerProgressDatabase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReaderProgressDbTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromRenderedPages(value: List<List<String>>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toRenderedPages(value: String): List<List<String>> {
        val listType = object : TypeToken<List<List<String>>>() {}.type
        return gson.fromJson(value, listType)
    }
}