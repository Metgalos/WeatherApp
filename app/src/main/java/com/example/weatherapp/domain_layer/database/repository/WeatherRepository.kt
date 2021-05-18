package com.example.weatherapp.domain_layer.database.repository

import androidx.paging.PagingSource
import com.example.weatherapp.data_layer.entity.Weather
import com.example.weatherapp.data_layer.response.Weather as WeatherResponse
import com.example.weatherapp.domain_layer.database.dao.WeatherDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherDao: WeatherDao) {

    suspend fun add(weather: Weather) {
        weatherDao.add(weather)
    }

    suspend fun addFromRequest(weather: WeatherResponse) {
        val insertedWeather = Weather(
            weather.location?.name,
            weather.current?.temperature,
            weather.current?.feelslike,
            weather.current?.icons?.first().toString(),
            SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US).format(Date())
        )

        add(insertedWeather)
    }

    suspend fun clear() {
        weatherDao.clear()
    }

    suspend fun delete(weather: Weather) {
        weatherDao.delete(weather)
    }

    fun getAllPaged(): PagingSource<Int, Weather> = weatherDao.getAllPaged()

    suspend fun getAll(page: Int, pageSize: Int): List<Weather> = weatherDao.getAll(page, pageSize)
}