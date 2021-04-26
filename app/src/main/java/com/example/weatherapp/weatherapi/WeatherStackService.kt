package com.example.weatherapp.weatherapi

import com.example.weatherapp.infrastructure.RetrofitClient

object WeatherStackService {
    private const val BASE_URL = "http://api.weatherstack.com/"
    val weatherApi: WeatherApi
        get() = RetrofitClient.getClient(BASE_URL).create(WeatherApi::class.java)
}