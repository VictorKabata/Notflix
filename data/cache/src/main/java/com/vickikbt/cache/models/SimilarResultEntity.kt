package com.vickikbt.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Similar_Result_Table")
data class SimilarResultEntity(

    @ColumnInfo(name = "Page")
    val page: Int?,

    @ColumnInfo(name = "Movies")
    val movieEntity: List<MovieEntity>?,

    @ColumnInfo(name = "Total_Pages")
    @PrimaryKey(autoGenerate = false)
    val total_pages: Int,

    @ColumnInfo(name = "Total_Results")
    val total_results: Int?
)