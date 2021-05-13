package com.example.weatherapp.presentation_layer.screen.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data_layer.entity.Weather
import com.example.weatherapp.presentation_layer.screen.history.viewholder.HistoryViewHolder
import com.example.weatherapp.presentation_layer.screen.history.viewholder.HistoryViewHolderListener

class WeatherListAdapter: RecyclerView.Adapter<HistoryViewHolder>() {

    private var weatherList = emptyList<Weather>()
    private var listener: HistoryViewHolderListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_row, parent, false))
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(weatherList[position], listener)
    }

    fun setData(weather: List<Weather>) {
        weatherList = weather
        notifyDataSetChanged()
    }

    fun setListener(listener: HistoryViewHolderListener) {
        this.listener = listener
    }
}