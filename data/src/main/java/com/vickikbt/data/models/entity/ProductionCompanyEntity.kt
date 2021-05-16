package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Production_Company_Table")
data class ProductionCompanyEntity(
    @ColumnInfo(name = "Company_ID")
    val id: Int?=null,

    @ColumnInfo(name = "Logo_Path")
    val logo_path: String?=null,

    @ColumnInfo(name = "Name")
    val name: String?=null,

    @ColumnInfo(name = "Original_Country")
    val origin_country: String?=null
)