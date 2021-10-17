package com.example.weatherapp.presentation.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.database.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.network.api.WeatherApi
import com.example.weatherapp.data.response.MainWeatherResponse
import com.example.weatherapp.data.storage.Storage
import com.example.weatherapp.domain.interactor.WeatherInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherRepositoryImpl: WeatherRepositoryImpl,
    private val storage: Storage,
    private val weatherInteractor: WeatherInteractor,
) : ViewModel() {

    private val _response = MutableLiveData<Response<MainWeatherResponse>>()
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
            storage.getLastCity()?.let { city -> getCurrentWeather(city) }
        }
    }

    private fun saveLastWeather(weatherResponse: Response<MainWeatherResponse>) {
        viewModelScope.launch {
            weatherResponse.body()?.let { weather ->
                if (weatherResponse.isSuccessful) {
                    weatherRepositoryImpl.addFromRequest(weather)
                    weather.location?.name?.let { city ->
                        storage.saveLastCity(city) }
                }
            }
        }
    }
}