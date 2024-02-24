package com.vickbt.shared.domain.models

class Episode(
    val id: String,
    val number: Int,
    val title: String = "",
    val released: String? = null,
    val poster: String? = null,
    var tvShow: Movie? = null,
    var season: Season? = null,
)
