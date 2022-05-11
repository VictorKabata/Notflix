package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.VideoEntity

class VideoEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(videoEntities: List<VideoEntity>): String? {
        if (videoEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<VideoEntity>>() {}.type
        return gson.toJson(videoEntities, type)
    }

    @TypeConverter
    fun to(videoEntitiesString: String?): List<VideoEntity>? {
        if (videoEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<VideoEntity>>() {}.type
        return gson.fromJson(videoEntitiesString, type)
    }
}
