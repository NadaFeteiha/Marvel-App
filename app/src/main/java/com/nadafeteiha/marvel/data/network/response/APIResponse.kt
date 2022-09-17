package com.nadafeteiha.marvel.data.network.response


import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("data")
    val dataResponse: DataResponse?,
    @SerializedName("status")
    val status: String?
)