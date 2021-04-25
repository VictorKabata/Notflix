package com.vickikbt.data.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.data.models.entity.ProductionCompanyEntity
import com.vickikbt.data.models.entity.ProductionCountryEntity

class ProductionCountryEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(productionCountryEntity: List<ProductionCountryEntity>): String? {
        if (productionCountryEntity.isNullOrEmpty()) return null

        val type = object : TypeToken<List<ProductionCountryEntity>?>() {}.type
        return gson.toJson(productionCountryEntity, type)
    }

    @TypeConverter
    fun to(productionCountryEntityString: String?): List<ProductionCountryEntity>? {
        if (productionCountryEntityString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<ProductionCountryEntity>?>() {}.type
        return gson.fromJson(productionCountryEntityString, type)
    }

}