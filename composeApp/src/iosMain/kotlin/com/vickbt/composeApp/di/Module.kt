package com.vickbt.composeApp.di

import com.vickbt.composeApp.utils.DatabaseFactory
import com.vickbt.composeApp.utils.DatastoreFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatastoreFactory().createDatastore() }
    single { DatabaseFactory().createDatabase() }
}
