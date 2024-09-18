package com.vickbt.composeApp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.vickbt.composeApp.domain.utils.Constants

actual class DatastoreFactory(private val context: Context) {
    actual fun createDatastore(): DataStore<Preferences> {
        return initDataStore {
            context.filesDir.resolve(Constants.DATASTORE_FILE_NAME).absolutePath
        }
    }
}
