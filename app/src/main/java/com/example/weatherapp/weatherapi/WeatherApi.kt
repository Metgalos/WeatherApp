package com.example.weatherapp.weatherapi

import com.example.weatherapp.weatherapi.models.Weather
import retrofit2.Response
import retrofit2.http.*

interface WeatherApi {

    companion object {
        const val BASE_URL = "http://api.weatherstack.com/"
    }

    @GET("current?access_key=48772b6ac1a5f8b2b5a273d748fd1ec2")
    suspend fun getCurrentWeather(@Query("query") query: String): Response<Weather>
}