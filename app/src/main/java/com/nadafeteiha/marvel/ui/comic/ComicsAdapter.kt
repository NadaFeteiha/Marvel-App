package com.nadafeteiha.marvel.ui.comic

import com.nadafeteiha.marvel.R
import com.nadafeteiha.marvel.data.network.response.ResponseItem
import com.nadafeteiha.marvel.ui.base.BaseAdapter


class ComicsAdapter(items: List<ResponseItem>) : BaseAdapter<ResponseItem>(items, null) {
    override val layoutID: Int = R.layout.item_comic
}