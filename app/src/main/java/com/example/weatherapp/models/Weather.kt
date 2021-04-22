package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("request") var request: Request?,
    @SerializedName("location") var location: Location?,
    @SerializedName("current") var current: Current?
)