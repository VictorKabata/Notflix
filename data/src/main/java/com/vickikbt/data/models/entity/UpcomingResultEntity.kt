package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Upcoming_Result_Table")
data class UpcomingResultEntity(

    @Embedded(prefix = "Upcoming_Dates")
    val upcomingDates: UpcomingDatesEntity? = null,

    @ColumnInfo(name = "Page")
    @PrimaryKey(autoGenerate = false)
    val page: Int,

    @ColumnInfo(name = "Movies")
    val moviesEntity: List<MovieEntity>? = null,

    @ColumnInfo(name = "Total_Page")
    val total_pages: Int? = null,

    @ColumnInfo(name = "Total_Results")
    val total_results: Int? = null
)