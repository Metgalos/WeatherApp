package com.example.weatherapp.domain_layer.storage

import android.content.Context
import android.content.SharedPreferences

class StorageImpl(private val context: Context) : Storage {
    override val preferences: SharedPreferences
        get() = context.getSharedPreferences(Storage.PREFERENCES_NAME, Context.MODE_PRIVATE)

    override suspend fun getLastCity(): String? = preferences.getString(LAST_CITY_KEY, null)

    override suspend fun saveLastCity(city: String) {
        preferences.edit().putString(LAST_CITY_KEY, city).apply()
    }

    companion object {
        private const val LAST_CITY_KEY = "last_city"
    }
}