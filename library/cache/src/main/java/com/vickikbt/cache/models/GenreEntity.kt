package com.vickikbt.cache.models

import androidx.room.ColumnInfo

data class GenreEntity(
    @ColumnInfo(name = "Genre_ID")
    val id: Int,

    @ColumnInfo(name = "Name")
    val name: String?
)