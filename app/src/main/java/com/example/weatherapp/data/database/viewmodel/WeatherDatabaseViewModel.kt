package com.example.weatherapp.data.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.weatherapp.data.database.repository.HistoryPagingSource
import com.example.weatherapp.data.entity.WeatherEntity as WeatherEntity
import com.example.weatherapp.data.database.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDatabaseViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 1
    }

    val history: StateFlow<PagingData<WeatherEntity>> =
        Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                HistoryPagingSource(repository)
            }
        ).flow
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