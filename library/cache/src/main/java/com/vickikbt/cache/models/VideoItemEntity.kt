package com.vickikbt.cache.models

import androidx.room.ColumnInfo

data class VideoItemEntity(
    @ColumnInfo(name = "ID")
    val id: String,

    @ColumnInfo(name = "ISO_3166_1")
    val iso_3166_1: String?,

    @ColumnInfo(name = "ISO_639_1")
    val iso_639_1: String?,

    @ColumnInfo(name = "Key")
    val key: String?,

    @ColumnInfo(name = "Name")
    val name: String?,

    @ColumnInfo(name = "Site")
    val site: String?,

    @ColumnInfo(name = "Size")
    val size: Int?,

    @ColumnInfo(name = "Type")
    val type: String?
)