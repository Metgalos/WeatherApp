package com.example.weatherapp.domain_layer.database.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.weatherapp.data_layer.entity.Weather
import com.example.weatherapp.domain_layer.database.repository.WeatherRepository
import kotlinx.coroutines.coroutineScope

class WeatherHistoryPagingSource(
    private val repository: WeatherRepository
) : PagingSource<Int, Weather>() {
    override fun getRefreshKey(state: PagingState<Int, Weather>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Weather> {
        val page: Int = params.key ?: INITIAL_PAGE_NUMBER
        val pageSize: Int = params.loadSize
        val weathers = repository.get(page, pageSize)

        return LoadResult.Page(
            data = weathers,
            prevKey = null,
            nextKey = if (weathers.size < pageSize) null else page + 1
        )
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}