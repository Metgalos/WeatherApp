package com.example.weatherapp.api

import com.example.weatherapp.models.Weather
import retrofit2.http.*

interface WeatherService {
    @GET("current?access_key=48772b6ac1a5f8b2b5a273d748fd1ec2")
    suspend fun getCurrentWeather(@Query("query") query: String): Weather
}