package com.example.weatherapp.domain_layer.database.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data_layer.entity.Weather as WeatherEntity
import com.example.weatherapp.data_layer.response.Weather as WeatherResponse
import com.example.weatherapp.domain_layer.database.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherDatabaseViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    val getAll: LiveData<List<WeatherEntity>> = repository.getAll

    fun add(weather: WeatherEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(weather)
        }
    }

    fun addFromResponse(weather: WeatherResponse) {
        val insertedWeather = WeatherEntity(
            weather.location?.name,
            weather.current?.temperature,
            weather.current?.feelslike,
            weather.current?.icons?.first().toString(),
            SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US).format(Date())
        )

        add(insertedWeather)
    }
}