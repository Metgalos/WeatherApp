package com.example.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class MeasureWeatherResponse(
    val humidity: Int?,
    val pressure: Int?,
    val temp: Double?,

    @SerializedName("feels_like")
    val feelsLike: Double?,

    @SerializedName("temp_max")
    val tempMax: Double?,

    @SerializedName("temp_min")
    val tempMin: Double?,
)