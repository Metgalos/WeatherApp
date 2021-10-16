package com.example.weatherapp.presentation.screen.history.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.entity.WeatherEntity
import com.example.weatherapp.data.model.LoadPhotoConfig
import com.example.weatherapp.domain.service.image_loader.GlideImageLoader
import com.example.weatherapp.extensions.toUiDateFormat
import kotlinx.android.synthetic.main.weather_row.view.*


class HistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: WeatherEntity, listener: HistoryViewHolderListener?) {
        itemView.rowCityName.text = data.location
        itemView.temperatureValue.text = data.temperature.toString()
        itemView.temperatureFeelsValue.text = data.feelslike.toString()
        itemView.datetimeText.text = data.responseDatetime?.toUiDateFormat()

        GlideImageLoader.load(
            LoadPhotoConfig(data.icon!!),
            itemView.rowIconImageView
        )

        itemView.deleteItemButton.setOnClickListener {
            listener?.onDeleteItem(data)
        }
    }
}