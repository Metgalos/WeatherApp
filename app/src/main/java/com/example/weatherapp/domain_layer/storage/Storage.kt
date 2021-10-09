package com.example.weatherapp.domain_layer.storage

import android.content.SharedPreferences

interface Storage {
    val preferences: SharedPreferences

    suspend fun getLastCity(): String?
    suspend fun saveLastCity(city: String)

    companion object {
        const val PREFERENCES_NAME = "main_preference"
    }
}