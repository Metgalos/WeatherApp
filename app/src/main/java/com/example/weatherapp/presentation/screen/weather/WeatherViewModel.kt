package com.example.weatherapp.presentation.screen.weather

import androidx.lifecycle.ViewModel
import timber.log.Timber

class WeatherViewModel : ViewModel() {

    fun getWeather(latitude: Double, longitude: Double) {
        // TODO: implement
        Timber.i("Latitude = $latitude and longitude = $longitude")
    }
}