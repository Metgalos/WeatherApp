package com.example.weatherapp.presentation.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.response.MainWeatherResponse
import com.example.weatherapp.domain.interactor.WeatherInteractor
import com.example.weatherapp.domain.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val weatherInteractor: WeatherInteractor,
) : ViewModel() {

    private val _response = MutableLiveData<Weather>()
    val response: LiveData<Response<MainWeatherResponse>> = _response

    init {
        getLastWeather()
    }

    fun getCurrentWeather(city: String) {
        viewModelScope.launch {
            _response.value = weatherInteractor.getCurrentWeather(city)
        }
    }

    private fun getLastWeather() {
        viewModelScope.launch {
            weatherInteractor.getLastWeather()
            storage.getLastCity()?.let { city -> getCurrentWeather(city) }
        }
    }
}