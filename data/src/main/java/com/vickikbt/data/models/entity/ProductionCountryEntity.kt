package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Production_Country_Table")
data class ProductionCountryEntity(
    @ColumnInfo(name = "ISO_3166_1")
    val iso_3166_1: String,

    @ColumnInfo(name = "Name")
    val name: String
)