package com.vickikbt.data.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.data.models.entity.SpokenLanguageEntity

class SpokenLanguageEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(spokenLanguageEntity: List<SpokenLanguageEntity>): String? {
        if (spokenLanguageEntity.isNullOrEmpty()) return null

        val type = object : TypeToken<List<SpokenLanguageEntity>?>() {}.type
        return gson.toJson(spokenLanguageEntity, type)
    }

    @TypeConverter
    fun to(spokenLanguageEntityString: String?): List<SpokenLanguageEntity>? {
        if (spokenLanguageEntityString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<SpokenLanguageEntity>?>() {}.type
        return gson.fromJson(spokenLanguageEntityString, type)
    }

}