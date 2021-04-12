package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo


data class SpokenLanguageEntity(

    @ColumnInfo(name = "English Name")
    val englishName: String,

    @ColumnInfo(name = "ISO6391")
    val iso6391: String,

    @ColumnInfo(name = "Name")
    val name: String
)