package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cast_Table")
data class CastEntity(
    @ColumnInfo(name = "Cast")
    val cast: List<CastItemEntity>,

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    val id: Int
)