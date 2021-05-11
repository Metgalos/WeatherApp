package com.example.weatherapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.infrastructure.DateFormat
import com.example.weatherapp.infrastructure.image.GlideImageLoader
import com.example.weatherapp.infrastructure.image.LoadPhotoConfig
import com.example.weatherapp.weatherstore.Weather
import kotlinx.android.synthetic.main.weather_row.view.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherListAdapter: RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    private var weatherList = emptyList<Weather>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_row, parent, false))
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = weatherList[position]
        holder.itemView.rowCityName.text = currentItem.location
        holder.itemView.temperatureValue.text = currentItem.temperature.toString()
        holder.itemView.temperatureFeelsValue.text = currentItem.feelslike.toString()
        holder.itemView.datetimeText.text = getFormattedDate(currentItem.responseDatetime!!)

        GlideImageLoader.load(
            LoadPhotoConfig(currentItem.icon!!),
            holder.itemView.rowIconImageView
        )
    }

    private fun getFormattedDate(rawDate: String): String {
        val parser = SimpleDateFormat(DateFormat.DB_DATE_FORMAT, Locale.US)
        val formatter = SimpleDateFormat(DateFormat.UI_DATE_FORMAT, Locale.US)
        return formatter.format(parser.parse(rawDate))
    }

    fun setData(weather: List<Weather>) {
        weatherList = weather
        notifyDataSetChanged()
    }
}