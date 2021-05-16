package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Popular_Result_Table")
data class PopularResultEntity(

    @ColumnInfo(name = "Page")
    @PrimaryKey(autoGenerate = false)
    val page: Int,

    @ColumnInfo(name = "Movies")
    val movieEntity: List<MovieEntity>? = null,

    @ColumnInfo(name = "Total_Pages")
    val total_pages: Int? = null,

    @ColumnInfo(name = "Total_Results")
    val total_results: Int? = null
)