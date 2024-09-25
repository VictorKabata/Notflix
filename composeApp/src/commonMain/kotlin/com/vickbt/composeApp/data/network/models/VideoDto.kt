package com.vickbt.composeApp.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(

    @SerialName("id")
    val id: String? = null,

    @SerialName("iso_3166_1")
    val iso31661: String? = null,

    @SerialName("iso_639_1")
    val iso6391: String? = null,

    @SerialName("key")
    val key: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("official")
    val official: Boolean? = null,

    @SerialName("published_at")
    val publishedAt: String? = null,

    @SerialName("site")
    val site: String? = null,

    @SerialName("size")
    val size: Int? = null,

    @SerialName("type")
    val type: String? = null
)
