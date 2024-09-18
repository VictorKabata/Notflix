package com.vickbt.shared.di

import com.vickbt.shared.utils.DatastoreFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatastoreFactory().createDatastore() }
    // single { RoomDbFactory().builder().getRoomDatabase() }
}
