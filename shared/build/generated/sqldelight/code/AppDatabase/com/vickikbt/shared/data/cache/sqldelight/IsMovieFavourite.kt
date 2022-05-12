package com.vickikbt.shared.`data`.cache.sqldelight

import kotlin.String

public data class IsMovieFavourite(
  public val isFavourite: String?
) {
  public override fun toString(): String = """
  |IsMovieFavourite [
  |  isFavourite: $isFavourite
  |]
  """.trimMargin()
}
