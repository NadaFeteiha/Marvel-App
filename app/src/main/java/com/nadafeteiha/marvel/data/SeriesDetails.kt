package com.nadafeteiha.marvel.data

import com.nadafeteiha.marvel.data.network.response.ResponseItem

data class SeriesDetails (val seriesResponse: ResponseItem,
                          val characterResponse: List<ResponseItem>)