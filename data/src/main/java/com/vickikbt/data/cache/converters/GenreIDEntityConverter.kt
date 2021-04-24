package com.vickikbt.data.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIDEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(genreEntities: List<Int>): String? {
        if (genreEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<Int>>() {}.type
        return gson.toJson(genreEntities, type)
    }

    @TypeConverter
    fun to(genreEntitiesString: String?): List<Int>? {
        if (genreEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(genreEntitiesString, type)
    }

}