package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity


data class CastItemEntity(
    @ColumnInfo(name = "Adult")
    val adult: Boolean?=null,

    @ColumnInfo(name = "Cast_ID")
    val cast_id: Int?=null,

    @ColumnInfo(name = "Character")
    val character: String?=null,

    @ColumnInfo(name = "Credit_ID")
    val credit_id: String?=null,

    @ColumnInfo(name = "Gender")
    val gender: Int?=null,

    @ColumnInfo(name = "ID")
    val id: Int?=null,

    @ColumnInfo(name = "Name")
    val name: String?=null,

    @ColumnInfo(name = "Order")
    val order: Int?=null,

    @ColumnInfo(name = "Original_Name")
    val original_name: String?=null,

    @ColumnInfo(name = "Popularity")
    val popularity: Double?=null,

    @ColumnInfo(name = "Profile_Path")
    val profile_path: String?=null
)