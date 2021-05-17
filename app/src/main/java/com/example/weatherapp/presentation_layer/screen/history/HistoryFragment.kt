package com.example.weatherapp.presentation_layer.screen.history

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.weatherapp.R
import com.example.weatherapp.data_layer.entity.Weather
import com.example.weatherapp.databinding.FragmentHistoryBinding
import com.example.weatherapp.domain_layer.database.viewmodel.WeatherDatabaseViewModel
import com.example.weatherapp.presentation_layer.screen.history.adapter.WeatherListAdapter
import com.example.weatherapp.presentation_layer.screen.history.viewholder.HistoryViewHolderListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val dbViewModel: WeatherDatabaseViewModel by viewModels()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        WeatherListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_history, container, false)

        setDeleteItemListener()
        addLoadListener()

        binding.historyRecycleView.adapter = adapter
        binding.historyRecycleView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            dbViewModel.history.collectLatest { adapter.submitData(it) }
        }

        return binding.root
    }

    private fun addLoadListener() {
        adapter.addLoadStateListener {
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.loadStateFlow.collectLatest { loadState ->
                    binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
                }
            }
        }
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

    private fun setDeleteItemListener() {
        adapter.setListener(object : HistoryViewHolderListener {
            override fun onDeleteItem(weather: Weather) {
                dbViewModel.delete(weather)
            }
        })
    }
}