package com.example.weatherapp.presentation_layer.screen.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.R
import com.example.weatherapp.data_layer.entity.Weather
import com.example.weatherapp.presentation_layer.screen.history.viewholder.HistoryViewHolder
import com.example.weatherapp.presentation_layer.screen.history.viewholder.HistoryViewHolderListener

class WeatherListAdapter : PagingDataAdapter<Weather, HistoryViewHolder>(diffCallback) {

    private var listener: HistoryViewHolderListener? = null

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Weather>() {
            override fun areItemsTheSame(old: Weather, new: Weather): Boolean = (old.id == new.id)

            override fun areContentsTheSame(old: Weather, new: Weather): Boolean = (old == new)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_row, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        getItem(position)?.let { weather -> holder.bind(weather, listener) }
    }

    fun setListener(listener: HistoryViewHolderListener) {
        this.listener = listener
    }
}