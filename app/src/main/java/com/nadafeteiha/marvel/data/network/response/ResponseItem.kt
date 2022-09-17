package com.nadafeteiha.marvel.data.network.response


import com.google.gson.annotations.SerializedName

data class ResponseItem(
    @SerializedName("description")
    val description: String?,
    @SerializedName("endYear")
    val endYear: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("startYear")
    val startYear: Int?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
)