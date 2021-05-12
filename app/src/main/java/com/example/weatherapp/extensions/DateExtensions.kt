package com.example.weatherapp.extensions

import java.text.SimpleDateFormat
import java.util.*


fun String.toUiDateFormat(): String {
    return changeDateFormat(this, "yyyy/MM/dd HH:mm:ss", "dd MMMM yyyy HH:mm")
}

fun String.toDbDateFormat(): String {
    return changeDateFormat(this, "dd MMMM yyyy HH:mm", "yyyy/MM/dd HH:mm:ss")
}

private fun changeDateFormat(string: String, from: String, to: String): String {
    val parser = SimpleDateFormat(from, Locale.US)
    val formatter = SimpleDateFormat(to, Locale.US)
    return formatter.format(parser.parse(string)!!)
}