package com.example.weatherapp.data.model

data class LoadPhotoConfig(
    val url: String,
    val placeholderRes: Int? = null,
    val errorRes: Int? = null,
)