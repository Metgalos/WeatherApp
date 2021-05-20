package com.example.weatherapp.presentation_layer.screen.search

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.domain_layer.network.viewmodel.WeatherApiViewModel
import com.example.weatherapp.domain_layer.service.image_loader.GlideImageLoader
import com.example.weatherapp.data_layer.model.LoadPhotoConfig
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private val apiViewModel: WeatherApiViewModel by viewModels()

    companion object {
        private const val LOCATION_ARGS_KEY = "location"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        setResponseObserver()
        setCurrentWeatherButtonListeners()
        arguments?.getString(LOCATION_ARGS_KEY)?.let { apiViewModel.getCurrentWeather(it) }
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