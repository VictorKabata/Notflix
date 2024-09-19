@file:OptIn(ExperimentalForeignApi::class)

package com.vickbt.composeApp.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.vickbt.composeApp.domain.utils.Constants
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DatastoreFactory {
    actual fun createDatastore(): DataStore<Preferences> {
        return initDataStore {
            val directory = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )
            requireNotNull(directory).path() + "/${Constants.DATASTORE_FILE_NAME}"
        }
    }
}
