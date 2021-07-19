package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.GenreEntity

class GenreEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(genreEntities: List<GenreEntity>): String? {
        if (genreEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<GenreEntity>>() {}.type
        return gson.toJson(genreEntities, type)
    }

    @TypeConverter
    fun to(genreEntitiesString: String?): List<GenreEntity>? {
        if (genreEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<GenreEntity>>() {}.type
        return gson.fromJson(genreEntitiesString, type)
    }

}