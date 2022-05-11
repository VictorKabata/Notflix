package com.vickikbt.cache.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.vickikbt.shared.domain.utils.Constants.TIME_DATASTORE_KEY
import com.vickikbt.shared.domain.utils.Constants.TIME_DATASTORE_NAME
import kotlinx.coroutines.flow.first

class TimeDatastore constructor(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = TIME_DATASTORE_NAME)

    // Saves sync time to Datastore.
    suspend fun saveSyncTime(time: Long) {
        val dataStoreKey = stringPreferencesKey(TIME_DATASTORE_KEY)
        context.dataStore.edit { syncTime ->
            syncTime[dataStoreKey] = time.toString()
        }
    }

    // Gets last sync time to Datastore.
    suspend fun getSyncTime(): Long {
        val dataStoreKey = stringPreferencesKey(TIME_DATASTORE_KEY)
        val syncTime = context.dataStore.data.first()
        val lastSyncTime = syncTime[dataStoreKey]?.toLong()
        return lastSyncTime ?: 0
    }
}
