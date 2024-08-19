package com.vickbt.shared.di

import com.vickbt.shared.utils.DatabaseBuilder
import com.vickbt.shared.utils.DatastoreFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatastoreFactory(context = get()).createDatastore() }
    single { DatabaseBuilder.createDatabase(context = get()) }
}
