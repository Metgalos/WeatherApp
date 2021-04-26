package com.example.weatherapp.infrastructure.image

import android.widget.ImageView

interface ImageLoader {

    fun load(config: LoadPhotoConfig, imageView: ImageView)
}