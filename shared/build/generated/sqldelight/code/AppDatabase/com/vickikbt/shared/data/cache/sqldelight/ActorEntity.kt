package com.vickikbt.shared.`data`.cache.sqldelight

import kotlin.Int
import kotlin.String

public data class ActorEntity(
  public val castId: Int?,
  public val character: String?,
  public val creditId: String?,
  public val id: Int?,
  public val name: String?,
  public val originalName: String?,
  public val profilePath: String?
) {
  public override fun toString(): String = """
  |ActorEntity [
  |  castId: $castId
  |  character: $character
  |  creditId: $creditId
  |  id: $id
  |  name: $name
  |  originalName: $originalName
  |  profilePath: $profilePath
  |]
  """.trimMargin()
}
