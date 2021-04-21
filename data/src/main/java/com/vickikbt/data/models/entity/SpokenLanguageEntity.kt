package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Spoken Language Table")
data class SpokenLanguageEntity(

    @ColumnInfo(name = "English Name")
    val englishName: String,

    @ColumnInfo(name = "ISO6391")
    val iso6391: String,

    @ColumnInfo(name = "Name")
    @PrimaryKey(autoGenerate = false)
    val name: String
)