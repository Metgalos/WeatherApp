package com.example.weatherapp.repository

import com.example.weatherapp.api.WeatherStackService
import com.example.weatherapp.models.Weather

class Repository {

    suspend fun getCurrentWeather(city: String): Weather {
        return WeatherStackService.weatherService.getCurrentWeather(city)
    }
}