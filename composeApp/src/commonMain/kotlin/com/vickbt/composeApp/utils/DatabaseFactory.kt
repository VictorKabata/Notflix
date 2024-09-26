package com.vickbt.composeApp.utils

import com.vickbt.composeApp.data.cache.AppDatabase

expect class DatabaseFactory {

    fun createDatabase(): AppDatabase
}
