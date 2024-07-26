package com.vickbt.shared.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.vickbt.shared.domain.utils.Constants

actual class DatastoreFactory {
    actual fun createDatastore(): DataStore<Preferences> {
        return initDataStore {
            Constants.DATASTORE_FILE_NAME
        }
    }
}
