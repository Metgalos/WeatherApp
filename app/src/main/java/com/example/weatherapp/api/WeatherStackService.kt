package com.example.weatherapp.api

object WeatherStackService {
    private const val BASE_URL = "https://api.weatherstack.com"
    val weatherService: WeatherService
        get() = RetrofitClient.getClient(BASE_URL).create(WeatherService::class.java)
}