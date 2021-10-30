package com.example.weatherapp.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.weatherapp.extensions.dataStore
import com.example.weatherapp.extensions.get
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LastRequestStorage @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    val lastRequest: Flow<String> = context.dataStore.data
        .get(lastRequestKey, "")

    suspend fun saveLastRequest(request: String) {
        context.dataStore.edit { settings ->
            settings[lastRequestKey] = request
        }
    }

    companion object {
        private val lastRequestKey = stringPreferencesKey("lastRequest")
    }
}