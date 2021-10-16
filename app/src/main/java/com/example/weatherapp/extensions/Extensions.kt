package com.example.weatherapp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.weatherapp.data.model.LoadPhotoConfig
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toUiDateFormat(): String {
    return changeDateFormat(this, "yyyy/MM/dd HH:mm:ss", "dd MMMM yyyy HH:mm")
}

private fun changeDateFormat(string: String, from: String, to: String): String {
    return try {
        val parser = SimpleDateFormat(from, Locale.US)
        val formatter = SimpleDateFormat(to, Locale.US)
        formatter.format(parser.parse(string)!!)
    } catch (e: ParseException) {
        string
    }
}

fun ImageView.load(config: LoadPhotoConfig) {
    Glide.with(context)
        .load(config.url)
        .placeholder(config.placeholderRes ?: -1)
        .error(config.errorRes ?: -1)
        .into(this)
}