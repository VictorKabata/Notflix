package io.victorkabata.models

import kotlinx.serialization.Serializable

@Serializable
class VideoDto(
    val source: String,
    val subtitles: List<SubtitleDto> = listOf(),
) {

    @Serializable
    class SubtitleDto(
        val label: String,
        val file: String,
    )

    @Serializable
    class ServerDto(
        val id: String,
        val name: String,
        val src: String = "",
    )
}
