package com.vickbt.shared.domain.models

class Season(
    val id: String,
    val number: Int,
    val title: String = "",
    val poster: String = "",
    var tvShow: Movie? = null,
    var episodes: List<Episode> = listOf(),
)
