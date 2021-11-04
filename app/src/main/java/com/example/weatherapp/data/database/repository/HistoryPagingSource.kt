package com.example.weatherapp.data.database.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.weatherapp.data.entity.WeatherEntity
import com.example.weatherapp.domain.repository.WeatherRepository

class HistoryPagingSource(
    private val repository: WeatherRepository,
) : PagingSource<Int, WeatherEntity>() {

    override fun getRefreshKey(state: PagingState<Int, WeatherEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WeatherEntity> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        val weathers = repository.getAll(page, pageSize)

        return LoadResult.Page(
            data = weathers,
            prevKey = null,
            nextKey = if (weathers.size < pageSize) null else page + 1
        )
    }
}