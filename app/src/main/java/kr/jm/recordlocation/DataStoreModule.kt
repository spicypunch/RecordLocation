package kr.jm.recordlocation

import android.content.Context
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class DataStoreModule(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "dataStore")
    private val latitudeKey = doublePreferencesKey("latitude")
    private val longitudeeKey = doublePreferencesKey("longitude")
}중