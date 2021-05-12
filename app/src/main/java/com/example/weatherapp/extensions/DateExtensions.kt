package com.example.weatherapp.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


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