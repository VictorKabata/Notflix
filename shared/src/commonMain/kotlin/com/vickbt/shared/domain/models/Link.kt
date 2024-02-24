package com.vickbt.shared.domain.models

data class Link(
    val type: String = "",
    val link: String = "",
    val sources: List<String> = listOf(),
    val tracks: List<String> = listOf(),
    val title: String = "",
)
