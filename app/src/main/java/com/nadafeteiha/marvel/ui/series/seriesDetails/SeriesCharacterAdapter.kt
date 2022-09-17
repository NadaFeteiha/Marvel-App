package com.nadafeteiha.marvel.ui.series.seriesDetails

import com.nadafeteiha.marvel.R
import com.nadafeteiha.marvel.data.network.response.ResponseItem
import com.nadafeteiha.marvel.ui.base.BaseAdapter
import com.nadafeteiha.marvel.ui.base.BaseInteractionListener


interface SeriesCharacterInteractionListener : BaseInteractionListener {
    fun onClickSeries(characterID: Int)
}

class SeriesCharacterAdapter(items: List<ResponseItem>, listener: SeriesCharacterInteractionListener) :
    BaseAdapter<ResponseItem>(items, listener) {
    override val layoutID: Int = R.layout.item_character
}