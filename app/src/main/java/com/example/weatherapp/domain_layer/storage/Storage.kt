package com.example.weatherapp.domain_layer.storage

import android.content.SharedPreferences
import com.example.weatherapp.data_layer.entity.Weather

interface Storage {
    val preferences: SharedPreferences

    fun getLastCity(): String?
    fun saveLastCity(city: String)

    companion object {
        const val PREFERENCES_NAME = "main_preference"
    }
}