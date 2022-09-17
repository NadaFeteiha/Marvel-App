package com.nadafeteiha.marvel.ui.series

import com.nadafeteiha.marvel.R
import com.nadafeteiha.marvel.data.network.response.ResponseItem
import com.nadafeteiha.marvel.ui.base.BaseAdapter
import com.nadafeteiha.marvel.ui.base.BaseInteractionListener

interface SeriesInteractionListener : BaseInteractionListener {
    fun onClickSeries(seriesID: Int)
}

class SeriesAdapter(items: List<ResponseItem>, listener: SeriesInteractionListener) :
    BaseAdapter<ResponseItem>(items, listener) {
    override val layoutID: Int = R.layout.item_series
}