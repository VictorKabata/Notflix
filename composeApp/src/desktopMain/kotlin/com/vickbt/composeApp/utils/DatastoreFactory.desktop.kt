package com.vickbt.composeApp.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.vickbt.composeApp.domain.utils.Constants

actual class DatastoreFactory {
    actual fun createDatastore(): DataStore<Preferences> {
        return initDataStore {
            Constants.DATASTORE_FILE_NAME
        }
    }
}
