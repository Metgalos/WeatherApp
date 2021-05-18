package com.example.weatherapp.presentation_layer.screen.history.viewholder

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.view.*
import timber.log.Timber

class HistoryLoadStateHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(loadState: LoadState) {
        itemView.progressBar.isVisible = (loadState is LoadState.Loading)
    }
}