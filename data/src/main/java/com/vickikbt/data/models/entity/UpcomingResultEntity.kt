package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Upcoming Result Table")
data class UpcomingResultEntity(

    @Embedded(prefix = "Upcoming_Dates")
    val upcomingDates: UpcomingDatesEntity,

    @ColumnInfo(name = "Page")
    @PrimaryKey(autoGenerate = false)
    val page: Int,

    @ColumnInfo(name = "Movies")
    val moviesEntity: List<MovieEntity>,

    @ColumnInfo(name = "Total_Page")
    val total_pages: Int,

    @ColumnInfo(name = "Total_Results")
    val total_results: Int
)