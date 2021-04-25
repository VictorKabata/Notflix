package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Spoken_Language_Table")
data class SpokenLanguageEntity(
    @ColumnInfo(name = "English_Name")
    val english_name: String?,

    @ColumnInfo(name = "ISO_639_1")
    val iso_639_1: String?,

    @ColumnInfo(name = "Name")
    val name: String?
)