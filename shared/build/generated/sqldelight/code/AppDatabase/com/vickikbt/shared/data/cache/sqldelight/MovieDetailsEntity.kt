package com.vickikbt.shared.`data`.cache.sqldelight

import kotlin.Long
import kotlin.String

public data class MovieDetailsEntity(
  public val adult: String?,
  public val backdropPath: String?,
  public val homePage: String?,
  public val id: Long,
  public val imdbId: String?,
  public val originalLanguage: String?,
  public val originalTitle: String?,
  public val overview: String?,
  public val popularity: String?,
  public val posterPath: String?,
  public val releaseDate: String?,
  public val runtime: Long?,
  public val status: String?,
  public val tagline: String?,
  public val title: String?,
  public val video: String?,
  public val voteAverage: String?,
  public val voteCount: Long?
) {
  public override fun toString(): String = """
  |MovieDetailsEntity [
  |  adult: $adult
  |  backdropPath: $backdropPath
  |  homePage: $homePage
  |  id: $id
  |  imdbId: $imdbId
  |  originalLanguage: $originalLanguage
  |  originalTitle: $originalTitle
  |  overview: $overview
  |  popularity: $popularity
  |  posterPath: $posterPath
  |  releaseDate: $releaseDate
  |  runtime: $runtime
  |  status: $status
  |  tagline: $tagline
  |  title: $title
  |  video: $video
  |  voteAverage: $voteAverage
  |  voteCount: $voteCount
  |]
  """.trimMargin()
}
