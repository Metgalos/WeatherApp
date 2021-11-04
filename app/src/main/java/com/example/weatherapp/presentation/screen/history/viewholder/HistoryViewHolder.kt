package com.example.weatherapp.presentation.screen.history.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.WeatherEntity
import com.example.weatherapp.data.model.LoadPhotoConfig
import com.example.weatherapp.databinding.WeatherRowBinding
import com.example.weatherapp.extensions.getIconUrl
import com.example.weatherapp.extensions.load
import com.example.weatherapp.extensions.toUiDateFormat


class HistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = WeatherRowBinding.bind(itemView)

    fun bind(data: WeatherEntity, listener: HistoryViewHolderListener?) = with (binding) {
        rowCityName.text = data.location
        temperatureValue.text = data.temperature.toString()
        temperatureFeelsValue.text = data.feelslike.toString()
        datetimeText.text = data.timestamp?.toUiDateFormat()

        rowIconImageView.load(
            LoadPhotoConfig(
                url = data.icon.getIconUrl(),
                errorRes = R.drawable.ic_unknown_weather,
                placeholderRes = R.drawable.ic_unknown_weather,
            )
        )

        deleteItemButton.setOnClickListener {
            listener?.onDeleteItem(data)
        }
    }
}