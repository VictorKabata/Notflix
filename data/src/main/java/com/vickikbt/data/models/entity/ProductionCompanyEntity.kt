package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo


data class ProductionCompanyEntity(
    @ColumnInfo(name = "ID")
    val id: Int,

    @ColumnInfo(name = "Logo Path")
    val logoPath: String?,

    @ColumnInfo(name = "Name")
    val name: String,

    @ColumnInfo(name = "Original Country")
    val originCountry: String
)