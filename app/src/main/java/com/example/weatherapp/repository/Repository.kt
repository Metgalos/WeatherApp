package com.example.weatherapp.repository

import com.example.weatherapp.api.WeatherStackService
import com.example.weatherapp.models.Weather
import retrofit2.Response

class Repository {

    suspend fun getCurrentWeather(city: String): Response<Weather> {
        return WeatherStackService.weatherService.getCurrentWeather(city)
    }
}