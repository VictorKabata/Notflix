package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Production Company Table")
data class ProductionCompanyEntity(
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "Logo Path")
    val logoPath: String?,

    @ColumnInfo(name = "Name")
    val name: String,

    @ColumnInfo(name = "Original Country")
    val originCountry: String
)