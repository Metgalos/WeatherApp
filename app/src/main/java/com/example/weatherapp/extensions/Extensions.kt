package com.example.weatherapp.extensions

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.bumptech.glide.Glide
import com.example.weatherapp.data.model.LoadPhotoConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss"
private const val DATETIME_UI_FORMAT = "dd MMMM yyyy HH:mm"

fun String.toUiDateFormat(): String {
    return changeDateFormat(this, DATETIME_FORMAT, DATETIME_UI_FORMAT)
}

fun Long?.toUiDateFormat(): String {
    return if (this == null) ""
    else SimpleDateFormat(DATETIME_UI_FORMAT, Locale.getDefault()).format(Date(this))
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

fun ImageView.loadIcon(
    icon: String,
    placeholderResId: Int? = null,
    errorResId: Int? = null,
) {
    Glide.with(context)
        .load(icon.getIconUrl())
        .placeholder(placeholderResId ?: -1)
        .error(errorResId ?: -1)
        .into(this)
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

fun <T> Flow<Preferences>.get(key: Preferences.Key<T>, default: T): Flow<T> {
    return this.catch { exception ->
        if (exception is IOException) {
            Timber.e(exception)
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }
        .map { preference ->
            preference[key] ?: default
        }
}

fun Int?.emptyIfNull(): Int = this ?: 0
fun Double?.emptyIfNull(): Double = this ?: 0.00
fun String?.emptyIfNull(): String = this ?: ""

fun String?.getIconUrl(): String = "http://openweathermap.org/img/wn/$this@2x.png"
