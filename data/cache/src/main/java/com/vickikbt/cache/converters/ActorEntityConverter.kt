package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.ActorEntity

class ActorEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(actorItemEntities: List<ActorEntity>): String? {
        if (actorItemEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<ActorEntity>>() {}.type
        return gson.toJson(actorItemEntities, type)
    }

    @TypeConverter
    fun to(actorItemEntitiesString: String?): List<ActorEntity>? {
        if (actorItemEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<ActorEntity>>() {}.type
        return gson.fromJson(actorItemEntitiesString, type)
    }

}