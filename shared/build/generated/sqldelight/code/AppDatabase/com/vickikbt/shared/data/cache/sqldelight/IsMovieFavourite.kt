package com.vickikbt.shared.`data`.cache.sqldelight

import kotlin.Boolean
import kotlin.String

public data class IsMovieFavourite(
  public val isFavourite: Boolean?
) {
  public override fun toString(): String = """
  |IsMovieFavourite [
  |  isFavourite: $isFavourite
  |]
  """.trimMargin()
}
