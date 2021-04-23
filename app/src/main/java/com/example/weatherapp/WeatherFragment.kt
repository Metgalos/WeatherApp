package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.weatherapp.data.Weather
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.repository.Repository
import java.text.SimpleDateFormat
import java.util.*


class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var apiViewModel: WeatherApiViewModel
    private lateinit var dbViewModel: WeatherDatabaseViewModel

    companion object {
        private const val DEFAULT_CITY = "Moscow"
        private const val DATE_FORMAT = "yyyy/MM/dd HH:mm"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)

        dbViewModel = ViewModelProvider(this).get(WeatherDatabaseViewModel::class.java)

        val repository = Repository()
        val viewModelFactory = WeatherViewModelFactory(repository)
        apiViewModel = ViewModelProvider(this, viewModelFactory).get(WeatherApiViewModel::class.java)
        observeGetCurrentWeather()

        apiViewModel.getCurrentWeather(DEFAULT_CITY)

        getCurrentWeatherButtonListeners()

        return binding.root
    }

    private fun observeGetCurrentWeather() {
        apiViewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->

            if (response.isSuccessful) {
                Glide.with(this).load(response.body()?.current?.icons?.first()).into(binding.weatherIconImageView)
                binding.weather = response.body()
                insertWeatherHistory()
            } else {
                Log.d("Response", response.errorBody().toString())
                Toast.makeText(context, "Response error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun insertWeatherHistory() {
        val weatherBinding = binding.weather

        val weather = Weather(
            weatherBinding?.location?.name,
            weatherBinding?.current?.temperature,
            weatherBinding?.current?.feelslike,
            weatherBinding?.current?.icons?.first().toString(),
            getCurrentDateTime())

        dbViewModel.add(weather)
    }

    private fun getCurrentDateTime(): String {
        val format = SimpleDateFormat(DATE_FORMAT, Locale.US)
        return format.format(Date())
    }

    private fun getCurrentWeatherButtonListeners() {
        binding.getCurrentWeatherButton.setOnClickListener {
            apiViewModel.getCurrentWeather(binding.enterCityEdit.text.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.historyMenuItem -> this.findNavController().navigate(R.id.action_weatherFragment_to_historyFragment)
        }

        return true
    }
}