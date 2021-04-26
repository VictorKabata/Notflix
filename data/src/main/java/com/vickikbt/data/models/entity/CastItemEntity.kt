package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity


data class CastItemEntity(
    @ColumnInfo(name = "Adult")
    val adult: Boolean,

    @ColumnInfo(name = "Cast_ID")
    val cast_id: Int,

    @ColumnInfo(name = "Character")
    val character: String,

    @ColumnInfo(name = "Credit_ID")
    val credit_id: String,

    @ColumnInfo(name = "Gender")
    val gender: Int,

    @ColumnInfo(name = "ID")
    val id: Int,

    @ColumnInfo(name = "Name")
    val name: String,

    @ColumnInfo(name = "Order")
    val order: Int,

    @ColumnInfo(name = "Original_Name")
    val original_name: String,

    @ColumnInfo(name = "Popularity")
    val popularity: Double,

    @ColumnInfo(name = "Profile_Path")
    val profile_path: String
)