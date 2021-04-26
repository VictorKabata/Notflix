package com.vickikbt.data.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.data.models.entity.VideoItemEntity

class VideoItemEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(videoItemEntities: List<VideoItemEntity>): String? {
        if (videoItemEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<VideoItemEntity>>() {}.type
        return gson.toJson(videoItemEntities, type)
    }

    @TypeConverter
    fun to(videoItemEntitiesString: String?): List<VideoItemEntity>? {
        if (videoItemEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<VideoItemEntity>>() {}.type
        return gson.fromJson(videoItemEntitiesString, type)
    }

}