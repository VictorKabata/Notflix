package com.vickikbt.data.models.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")
    val backdrop_path: String?=null,

    @SerializedName("genre_ids")
    val genre_ids: List<Int>?=null,

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_language")
    val original_language: String?=null,

    @SerializedName("original_title")
    val original_title: String?=null,

    @SerializedName("overview")
    val overview: String?=null,

    @SerializedName("popularity")
    val popularity: Double?=null,

    @SerializedName("poster_path")
    val poster_path: String?=null,

    @SerializedName("release_date")
    val release_date: String?=null,

    @SerializedName("title")
    val title: String?=null,

    @SerializedName("video")
    val video: Boolean?=null,

    @SerializedName("vote_average")
    val vote_average: Double?=null,

    @SerializedName("vote_count")
    val vote_count: Int ?=null
)