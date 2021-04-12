package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo


data class GenreEntity(

    @ColumnInfo(name = "ID")
    val id: Int,

    @ColumnInfo(name = "Name")
    val name: String
)