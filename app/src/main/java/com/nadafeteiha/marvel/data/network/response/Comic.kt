package com.nadafeteiha.marvel.data.network.response


import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("text")
    val text: String?
)