package com.example.weatherapp.presentation.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.weatherapp.data.database.repository.HistoryPagingSource
import com.example.weatherapp.data.database.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.entity.WeatherEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repositoryImpl: WeatherRepositoryImpl
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
                HistoryPagingSource(repositoryImpl)
            }
        ).flow
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun add(weather: WeatherEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.add(weather)
        }
    }

    fun clear() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.clear()
        }
    }

    fun delete(weather: WeatherEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.delete(weather)
        }
    }
}