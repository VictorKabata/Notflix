package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo


data class ProductionCountryEntity(

    @ColumnInfo(name="ISO31661")
    val iso31661: String,

    @ColumnInfo(name="Name")
    val name: String
)