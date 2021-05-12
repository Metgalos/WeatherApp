package com.example.weatherapp.domain_layer.service.image_loader

import android.widget.ImageView
import com.example.weatherapp.data_layer.model.LoadPhotoConfig

interface ImageLoader {

    fun load(config: LoadPhotoConfig, imageView: ImageView)
}