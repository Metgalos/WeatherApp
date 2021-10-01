package com.example.weatherapp.domain_layer.network.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data_layer.response.Weather
import retrofit2.Response
import retrofit2.http.*

interface WeatherApi {

    companion object {
        const val BASE_URL = "http://api.weatherstack.com/"
    }

    @GET("current?access_key=${ BuildConfig.weatherstackApiKey }")
    suspend fun getCurrentWeather(@Query("query") query: String): Response<Weather>
}