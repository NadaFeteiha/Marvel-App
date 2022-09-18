package com.nadafeteiha.marvel.data.network

import com.nadafeteiha.marvel.data.network.response.APIResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {
    @GET("series")
    fun getSeries(): Single<Response<APIResponse>>

    @GET("comics")
    fun getComics(): Single<Response<APIResponse>>

    @GET("characters/{characterID}")
    fun getCharacterByID(
        @Path("characterID") characterID: Int
    ): Single<Response<APIResponse>>

    @GET("series/{seriesID}")
    fun getSeriesDetails(
        @Path("seriesID") seriesID: Int
    ): Single<Response<APIResponse>>


    @GET("series/{seriesID}/characters")
    fun getCharacterInSpecificSeries(
        @Path("seriesID") seriesID: Int
    ): Single<Response<APIResponse>>


}