package com.example.weatherapp.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHistoryBinding
import com.example.weatherapp.weatherstore.WeatherDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val dbViewModel: WeatherDatabaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_history, container, false)

        val adapter = WeatherListAdapter()

        binding.historyRecycleView.adapter = adapter
        binding.historyRecycleView.layoutManager = LinearLayoutManager(requireContext())

        dbViewModel.getAll.observe(viewLifecycleOwner, Observer { weathers ->
            adapter.setData(weathers)
        })

        return binding.root
    }
}