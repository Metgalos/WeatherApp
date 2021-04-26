package com.example.weatherapp.weatherapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherViewModelFactory(
    private val weatherService: WeatherService
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherApiViewModel(
            weatherService
        ) as T
    }
}