package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.ProductionCompanyEntity

class ProductionCompanyEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(productionCompanyEntity: List<ProductionCompanyEntity>): String? {
        if (productionCompanyEntity.isNullOrEmpty()) return null

        val type = object : TypeToken<List<ProductionCompanyEntity>?>() {}.type
        return gson.toJson(productionCompanyEntity, type)
    }

    @TypeConverter
    fun to(productionCompaniesEntityString: String?): List<ProductionCompanyEntity>? {
        if (productionCompaniesEntityString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<ProductionCompanyEntity>?>() {}.type
        return gson.fromJson(productionCompaniesEntityString, type)
    }

}