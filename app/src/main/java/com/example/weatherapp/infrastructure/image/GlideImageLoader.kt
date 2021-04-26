package com.example.weatherapp.infrastructure.image

import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideImageLoader: ImageLoader {
    override fun load(config: LoadPhotoConfig, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(config.url)
            .placeholder(config.placeholder ?: -1)
            .error(config.placeholder ?: -1)
            .into(imageView)
    }

}