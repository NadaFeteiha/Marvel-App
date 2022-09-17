package com.nadafeteiha.marvel.data.network.response


import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("results")
    val itemsResponse: List<ResponseItem>?,
    @SerializedName("total")
    val total: Int?
)