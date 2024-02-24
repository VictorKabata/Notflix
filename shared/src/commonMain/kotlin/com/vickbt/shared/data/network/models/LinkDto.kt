package io.victorkabata.models

import kotlinx.serialization.Serializable

@Serializable
data class LinkDto(
    val type: String = "",
    val link: String = "",
    val sources: List<String> = listOf(),
    val tracks: List<String> = listOf(),
    val title: String = "",
)
