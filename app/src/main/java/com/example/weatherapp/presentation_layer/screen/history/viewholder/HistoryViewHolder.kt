package com.example.weatherapp.presentation_layer.screen.history.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data_layer.entity.Weather
import com.example.weatherapp.data_layer.model.LoadPhotoConfig
import com.example.weatherapp.domain_layer.service.image_loader.GlideImageLoader
import com.example.weatherapp.extensions.toUiDateFormat
import kotlinx.android.synthetic.main.weather_row.view.*


class HistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: Weather, listener: HistoryViewHolderListener?) {
        itemView.rowCityName.text = data.location
        itemView.temperatureValue.text = data.temperature.toString()
        itemView.temperatureFeelsValue.text = data.feelslike.toString()
        itemView.datetimeText.text = data.responseDatetime?.toUiDateFormat()

        GlideImageLoader.load(
            LoadPhotoConfig(data.icon!!),
            itemView.rowIconImageView
        )

        itemView.deleteItemButton.setOnClickListener { listener?.onDeleteItem(data) }

        data.location?.let { location ->
            itemView.revertButton.setOnClickListener { listener?.onRepeateResponse(location) }
        }
    }
}