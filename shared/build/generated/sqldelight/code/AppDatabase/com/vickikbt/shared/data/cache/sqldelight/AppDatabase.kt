package com.vickikbt.shared.`data`.cache.sqldelight

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import com.vickikbt.shared.`data`.cache.sqldelight.shared.newInstance
import com.vickikbt.shared.`data`.cache.sqldelight.shared.schema

public interface AppDatabase : Transacter {
  public val appDatabaseQueries: AppDatabaseQueries

  public companion object {
    public val Schema: SqlDriver.Schema
      get() = AppDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): AppDatabase =
        AppDatabase::class.newInstance(driver)
  }
}
