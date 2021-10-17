package com.example.weatherapp.data.database.repository

import androidx.paging.PagingSource
import com.example.weatherapp.data.entity.WeatherEntity
import com.example.weatherapp.data.response.MainWeatherResponse as WeatherResponse
import com.example.weatherapp.data.database.dao.WeatherDao
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.data.network.api.WeatherApi
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApi,
    private val mapper: WeatherMapper,
) : WeatherRepository {

    suspend fun add(weatherEntity: WeatherEntity) {
        weatherDao.add(weatherEntity)
    }

    suspend fun addFromRequest(weather: WeatherResponse) {
        val insertedWeather = WeatherEntity(
            weather.location?.name,
            weather.currentResponse?.temperature,
            weather.currentResponse?.feelslike,
            weather.currentResponse?.icons?.first().toString(),
            SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US).format(Date())
        )

        add(insertedWeather)
    }

    suspend fun clear() {
        weatherDao.clear()
    }

    suspend fun delete(weatherEntity: WeatherEntity) {
        weatherDao.delete(weatherEntity)
    }

    fun getAllPaged(): PagingSource<Int, WeatherEntity> = weatherDao.getAllPaged()

    suspend fun getAll(page: Int, pageSize: Int): List<WeatherEntity> = weatherDao.getAll(page, pageSize)

    override suspend fun getCurrent(city: String): Weather {
        val weather = weatherApi.getCurrentWeather(city)
        return mapper.mapWeather(weather)
    }

    override suspend fun save(weather: Weather) {
        val entity = mapper.mapEntity(weather)
        weatherDao.add(entity)
    }
}