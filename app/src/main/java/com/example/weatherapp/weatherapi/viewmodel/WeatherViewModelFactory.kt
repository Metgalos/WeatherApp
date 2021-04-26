package com.example.weatherapp.weatherapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.weatherapi.WeatherService

class WeatherViewModelFactory(
    private val weatherService: WeatherService
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherApiViewModel(
            weatherService
        ) as T
    }
}