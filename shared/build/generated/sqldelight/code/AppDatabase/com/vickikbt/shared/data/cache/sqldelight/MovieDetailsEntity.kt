package com.vickikbt.shared.`data`.cache.sqldelight

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String

public data class MovieDetailsEntity(
  public val adult: Boolean?,
  public val backdropPath: String?,
  public val homepage: String?,
  public val id: Int,
  public val imdbId: String?,
  public val originalLanguage: String?,
  public val originalTitle: String?,
  public val overview: String?,
  public val popularity: Double?,
  public val posterPath: String?,
  public val releaseDate: String?,
  public val runtime: Int?,
  public val status: String?,
  public val tagline: String?,
  public val title: String?,
  public val video: Boolean?,
  public val voteAverage: Double?,
  public val voteCount: Int?
) {
  public override fun toString(): String = """
  |MovieDetailsEntity [
  |  adult: $adult
  |  backdropPath: $backdropPath
  |  homepage: $homepage
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
