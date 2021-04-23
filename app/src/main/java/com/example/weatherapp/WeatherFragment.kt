package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.repository.Repository


class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)

        val repository = Repository()
        val viewModelFactory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)

        viewModel.myResponse.observe(this, Observer { response ->

            if (response.isSuccessful) {
                Glide.with(this).load(response.body()?.current?.icons?.first()).into(binding.weatherIconImageView)
                binding.weather = response.body()
            } else {
                Log.d("Response", response.errorBody().toString())
                Toast.makeText(context, "Response error", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getCurrentWeather(DEFAULT_CITY)

        binding.getCurrentWeatherButton.setOnClickListener {
            viewModel.getCurrentWeather(binding.enterCityEdit.text.toString())
        }

        return binding.root
    }

    companion object {
        private const val DEFAULT_CITY = "Moscow"
    }
}