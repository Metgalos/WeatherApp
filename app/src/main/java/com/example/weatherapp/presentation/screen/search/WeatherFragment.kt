package com.example.weatherapp.presentation.screen.search

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.data.network.viewmodel.WeatherApiViewModel
import com.example.weatherapp.domain.service.image_loader.GlideImageLoader
import com.example.weatherapp.data.model.LoadPhotoConfig
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private val apiViewModel: WeatherApiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        setResponseObserver()
        setCurrentWeatherButtonListeners()
        return binding.root
    }

    private fun setResponseObserver() {
        apiViewModel.response.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {
                GlideImageLoader.load(
                    LoadPhotoConfig(it.body()?.current?.icons?.first().toString()),
                    binding.weatherIconImageView
                )
                binding.enterCityEdit.setText(it.body()?.location?.name)
                binding.weather = it.body()
                binding.weatherDataLayout.visibility = View.VISIBLE
            } else {
                Log.d("Response", it.errorBody().toString())
                Toast.makeText(context, "Response error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setCurrentWeatherButtonListeners() {
        binding.getCurrentWeatherButton.setOnClickListener {
            apiViewModel.getCurrentWeather(binding.enterCityEdit.text.toString())
        }
    }
}