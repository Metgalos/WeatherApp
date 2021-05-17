package com.example.weatherapp.domain_layer.database.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.weatherapp.data_layer.entity.Weather as WeatherEntity
import com.example.weatherapp.domain_layer.database.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDatabaseViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val history: StateFlow<PagingData<WeatherEntity>> = Pager(PagingConfig(pageSize = 3)) {
        repository.getAllPaged
    }.flow
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun add(weather: WeatherEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(weather)
        }
    }

    fun clear() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clear()
        }
    }

    fun delete(weather: WeatherEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(weather)
        }
    }
}