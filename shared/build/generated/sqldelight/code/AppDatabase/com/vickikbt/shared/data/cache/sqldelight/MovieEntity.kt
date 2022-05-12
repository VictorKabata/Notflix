package com.vickikbt.shared.`data`.cache.sqldelight

import kotlin.Boolean
import kotlin.Double
import kotlin.Long
import kotlin.String

public data class MovieEntity(
  public val adult: Boolean?,
  public val backdropPath: String?,
  public val id: Long?,
  public val originalLanguage: String?,
  public val originalTitle: String?,
  public val overview: String?,
  public val popularity: Double?,
  public val posterPath: String?,
  public val releaseDate: String?,
  public val title: String?,
  public val video: Boolean?,
  public val voteAverage: Double?,
  public val voteCount: Long?,
  public val category: String?,
  public val isFavourite: Boolean?,
  public val cacheId: Long
) {
  public override fun toString(): String = """
  |MovieEntity [
  |  adult: $adult
  |  backdropPath: $backdropPath
  |  id: $id
  |  originalLanguage: $originalLanguage
  |  originalTitle: $originalTitle
  |  overview: $overview
  |  popularity: $popularity
  |  posterPath: $posterPath
  |  releaseDate: $releaseDate
  |  title: $title
  |  video: $video
  |  voteAverage: $voteAverage
  |  voteCount: $voteCount
  |  category: $category
  |  isFavourite: $isFavourite
  |  cacheId: $cacheId
  |]
  """.trimMargin()
}
