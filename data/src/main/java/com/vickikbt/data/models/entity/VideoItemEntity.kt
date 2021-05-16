package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo

data class VideoItemEntity(
    @ColumnInfo(name = "ID")
    val id: String? = null,

    @ColumnInfo(name = "ISO_3166_1")
    val iso_3166_1: String? = null,

    @ColumnInfo(name = "ISO_639_1")
    val iso_639_1: String? = null,

    @ColumnInfo(name = "Key")
    val key: String? = null,

    @ColumnInfo(name = "Name")
    val name: String? = null,

    @ColumnInfo(name = "Site")
    val site: String? = null,

    @ColumnInfo(name = "Size")
    val size: Int? = null,

    @ColumnInfo(name = "Type")
    val type: String? = null
)