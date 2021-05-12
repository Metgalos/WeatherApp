package com.example.weatherapp.domain_layer.service.image_loader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.weatherapp.data_layer.model.LoadPhotoConfig

object GlideImageLoader: ImageLoader {
    override fun load(config: LoadPhotoConfig, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(config.url)
            .placeholder(config.placeholder ?: -1)
            .error(config.placeholder ?: -1)
            .into(imageView)
    }

}