package com.vickbt.shared.domain.models

import com.vickbt.shared.domain.models.Episode
import com.vickbt.shared.domain.models.Movie
import kotlinx.serialization.Serializable

class Season(
    val id: String,
    val number: Int,
    val title: String = "",
    val poster: String = "",
    var tvShow: Movie? = null,
    var episodes: List<Episode> = listOf(),
)
