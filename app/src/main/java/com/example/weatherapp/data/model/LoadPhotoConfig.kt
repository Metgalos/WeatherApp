package com.example.weatherapp.data.model

import androidx.annotation.DrawableRes

data class LoadPhotoConfig(
    val url: String,

    @DrawableRes
    val errorRes: Int? = null,

    @DrawableRes
    val placeholderRes: Int? = null,
)