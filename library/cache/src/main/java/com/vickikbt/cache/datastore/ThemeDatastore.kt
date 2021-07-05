package com.vickikbt.cache.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.vickikbt.core.Constants.THEME_DATASTORE_KEY
import com.vickikbt.core.Constants.THEME_DATASTORE_NAME
import com.vickikbt.core.Coroutines
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

class ThemeDatastore constructor(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = THEME_DATASTORE_NAME)

    //Saves sync time to Datastore.
    fun saveSelectedTheme(selectedTheme: Int) {
        Timber.e("Setting app theme: $selectedTheme")

        val dataStoreKey = stringPreferencesKey(THEME_DATASTORE_KEY)
        Coroutines.main {
            context.dataStore.edit { theme ->
                theme[dataStoreKey] = selectedTheme.toString()
            }
        }
    }

    //Gets last sync time to Datastore.
    suspend fun getSavedTheme(): Flow<Int?> {
        val dataStoreKey = stringPreferencesKey(THEME_DATASTORE_KEY)
        val theme = context.dataStore.data.first()
        val value = theme[dataStoreKey]?.toInt()
        Timber.e("Saved theme is: $value")
        return flowOf(value ?: -1)
    }


}