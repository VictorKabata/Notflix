package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.CastItemEntity

class CastItemEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(castItemEntities: List<CastItemEntity>): String? {
        if (castItemEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<CastItemEntity>>() {}.type
        return gson.toJson(castItemEntities, type)
    }

    @TypeConverter
    fun to(castItemEntitiesString: String?): List<CastItemEntity>? {
        if (castItemEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<CastItemEntity>>() {}.type
        return gson.fromJson(castItemEntitiesString, type)
    }

}