package com.example.weatherapp.presentation_layer.screen.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.domain_layer.service.image_loader.GlideImageLoader
import com.example.weatherapp.data_layer.model.LoadPhotoConfig
import com.example.weatherapp.data_layer.entity.Weather
import com.example.weatherapp.extensions.toUiDateFormat
import kotlinx.android.synthetic.main.weather_row.view.*

class WeatherListAdapter: RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    private var weatherList = emptyList<Weather>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(data: Weather) {
            itemView.rowCityName.text = data.location
            itemView.temperatureValue.text = data.temperature.toString()
            itemView.temperatureFeelsValue.text = data.feelslike.toString()
            itemView.datetimeText.text = data.responseDatetime?.toUiDateFormat()

            GlideImageLoader.load(
                LoadPhotoConfig(data.icon!!),
                itemView.rowIconImageView
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_row, parent, false))
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    fun setData(weather: List<Weather>) {
        weatherList = weather
        notifyDataSetChanged()
    }
}