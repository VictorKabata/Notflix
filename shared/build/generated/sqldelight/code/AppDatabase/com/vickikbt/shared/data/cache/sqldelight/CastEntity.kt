package com.vickikbt.shared.`data`.cache.sqldelight

import ActorEntity
import List
import com.squareup.sqldelight.ColumnAdapter
import kotlin.Int
import kotlin.String

public data class CastEntity(
  public val actor: List<ActorEntity>?,
  public val id: Int
) {
  public override fun toString(): String = """
  |CastEntity [
  |  actor: $actor
  |  id: $id
  |]
  """.trimMargin()

  public class Adapter(
    public val actorAdapter: ColumnAdapter<List<ActorEntity>, String>
  )
}
