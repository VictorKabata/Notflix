package com.vickikbt.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Production_Company_Table")
data class ProductionCompanyEntity(
    @ColumnInfo(name = "Company_ID")
    val id: Int,

    @ColumnInfo(name = "Logo_Path")
    val logo_path: String?,

    @ColumnInfo(name = "Name")
    val name: String?,

    @ColumnInfo(name = "Original_Country")
    val origin_country: String?
)