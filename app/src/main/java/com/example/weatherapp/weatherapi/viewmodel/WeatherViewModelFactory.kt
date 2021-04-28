package com.example.weatherapp.weatherapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.weatherapi.WeatherApi
import javax.inject.Inject

class WeatherViewModelFactory @Inject constructor(
    private val weatherApi: WeatherApi
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherApiViewModel(
            weatherApi
        ) as T
    }
}