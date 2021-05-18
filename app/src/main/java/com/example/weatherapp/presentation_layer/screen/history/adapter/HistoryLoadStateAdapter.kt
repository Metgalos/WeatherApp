package com.example.weatherapp.presentation_layer.screen.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.weatherapp.R
import com.example.weatherapp.presentation_layer.screen.history.viewholder.HistoryLoadStateHolder

class HistoryLoaderStateAdapter : LoadStateAdapter<HistoryLoadStateHolder>() {

    override fun onBindViewHolder(holder: HistoryLoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): HistoryLoadStateHolder {
        return HistoryLoadStateHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.fragment_history, parent, false)
        )
    }

}
