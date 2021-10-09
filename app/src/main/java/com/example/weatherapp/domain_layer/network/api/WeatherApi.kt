package com.example.weatherapp.domain_layer.network.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data_layer.response.MainWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?appid=$apiKey")
    suspend fun getCurrentWeather(@Query("q") city: String): MainWeatherResponse

    companion object {
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        private const val apiKey = BuildConfig.API_KEY
    }
}
