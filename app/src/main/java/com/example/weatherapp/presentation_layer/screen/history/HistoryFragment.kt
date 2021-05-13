package com.example.weatherapp.presentation_layer.screen.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.data_layer.entity.Weather
import com.example.weatherapp.databinding.FragmentHistoryBinding
import com.example.weatherapp.domain_layer.database.viewmodel.WeatherDatabaseViewModel
import com.example.weatherapp.presentation_layer.screen.history.viewholder.HistoryViewHolderListener
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

        adapter.setListener(object : HistoryViewHolderListener {
            override fun onDeleteItem(weather: Weather) {
                dbViewModel.delete(weather)
            }
        })

        binding.historyRecycleView.adapter = adapter
        binding.historyRecycleView.layoutManager = LinearLayoutManager(requireContext())

        dbViewModel.getAll.observe(viewLifecycleOwner, Observer { weathers ->
            adapter.setData(weathers)
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.history_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clearHistory -> dbViewModel.clear()
        }

        return true
    }
}