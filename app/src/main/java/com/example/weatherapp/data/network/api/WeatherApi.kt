package com.example.weatherapp.data.network.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?appid=$apiKey")
    suspend fun getCurrentWeather(@Query("q") city: String): WeatherResponse

    companion object {
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        private const val apiKey = BuildConfig.API_KEY
    }
}
