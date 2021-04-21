package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Production Country Table")
data class ProductionCountryEntity(

    @ColumnInfo(name="ISO31661")
    val iso31661: String,

    @ColumnInfo(name="Name")
    @PrimaryKey(autoGenerate = false)
    val name: String
)