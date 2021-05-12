package com.example.weatherapp.presentation_layer.screen.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.data_layer.entity.Weather
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.domain_layer.network.viewmodel.WeatherApiViewModel
import com.example.weatherapp.domain_layer.service.image_loader.GlideImageLoader
import com.example.weatherapp.data_layer.model.LoadPhotoConfig
import com.example.weatherapp.domain_layer.database.viewmodel.WeatherDatabaseViewModel
import com.example.weatherapp.domain_layer.storage.Storage
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private val apiViewModel: WeatherApiViewModel by viewModels()
    private val dbViewModel: WeatherDatabaseViewModel by viewModels()
    @Inject lateinit var storage: Storage

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
        apiViewModel.responseEvent.observe(viewLifecycleOwner, { needSave ->
            apiViewModel.response.value?.let {
                if (it.isSuccessful) {
                    GlideImageLoader.load(
                        LoadPhotoConfig(it.body()?.current?.icons?.first().toString()),
                        binding.weatherIconImageView
                    )

                    binding.weather = it.body()
                    binding.weatherDataLayout.visibility = View.VISIBLE

                    if (needSave) {
                        dbViewModel.addFromResponse(binding.weather)
                        it.body()?.location?.name?.let { city -> storage.saveLastCity(city) }
                    }
                } else {
                    Log.d("Response", it.errorBody().toString())
                    Toast.makeText(context, "Response error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setCurrentWeatherButtonListeners() {
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
        activity?.currentFocus?.let { v ->
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }

        when (item.itemId) {
            R.id.historyMenuItem -> findNavController().navigate(
                R.id.action_weatherFragment_to_historyFragment
            )
        }

        return true
    }
}