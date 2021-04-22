package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("temperature") var temperature: Int,
    @SerializedName("weather_icons") var icons: List<String>,
    @SerializedName("weather_descriptions") var descriptions: List<String>,
    @SerializedName("feelslike") var feelslike: Int
)