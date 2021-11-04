package com.example.weatherapp.data.database.repository

import com.example.weatherapp.data.entity.WeatherEntity
import com.example.weatherapp.data.database.dao.WeatherDao
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.data.network.api.WeatherApi
import com.example.weatherapp.data.response.WeatherResponse
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherApi,
    private val mapper: WeatherMapper,
) : WeatherRepository {

    suspend fun add(weatherEntity: WeatherEntity) {
        weatherDao.add(weatherEntity)
    }

    suspend fun save(weather: WeatherResponse) {
        return add(
            WeatherEntity(
                location = weather.name,
                temperature = weather.measureResponse?.temp,
                feelslike = weather.measureResponse?.feelsLike,
                icon = weather.mainWeatherResponse?.firstOrNull()?.icon,
                timestamp = System.currentTimeMillis(),
            )
        )
    }

    suspend fun clear() {
        weatherDao.clear()
    }

    suspend fun delete(weatherEntity: WeatherEntity) {
        weatherDao.delete(weatherEntity)
    }

    override suspend fun getAll(page: Int, pageSize: Int): List<WeatherEntity> {
        return weatherDao.getAll(page, pageSize)
    }

    override suspend fun getCurrent(city: String): Weather {
        val weather = weatherApi.getCurrentWeather(city)
        return mapper.mapWeather(weather)
    }

    override suspend fun save(weather: Weather) {
        val entity = mapper.mapEntity(weather)
        weatherDao.add(entity)
    }
}