package com.example.weatherapp.weatherapi

import com.example.weatherapp.weatherapi.WeatherStackService
import com.example.weatherapp.weatherapi.models.Weather
import retrofit2.Response

class WeatherService {

    suspend fun getCurrentWeather(city: String): Response<Weather> {
        return WeatherStackService.weatherApi.getCurrentWeather(city)
    }
}