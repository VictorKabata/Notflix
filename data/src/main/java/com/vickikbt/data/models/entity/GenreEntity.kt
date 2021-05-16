package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo

data class GenreEntity(
    @ColumnInfo(name = "Genre_ID")
    val id: Int?=null,

    @ColumnInfo(name = "Name")
    val name: String?=null
)