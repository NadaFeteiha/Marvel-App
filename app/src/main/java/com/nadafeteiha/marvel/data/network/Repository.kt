package com.nadafeteiha.marvel.data.network

import com.nadafeteiha.marvel.data.SeriesDetails
import com.nadafeteiha.marvel.data.network.response.APIResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class Repository {

    fun getSeries() = wrapperWithState { Api.marvelService.getSeries() }

    fun getComics() = wrapperWithState { Api.marvelService.getComics() }

    fun getCharacterByID(characterID: Int) =
        wrapperWithState { Api.marvelService.getCharacterByID(characterID) }

    private fun getSeriesDetails(seriesID: Int) = wrapperWithState {
        Api.marvelService.getSeriesDetails(seriesID = seriesID)
    }

    private fun getCharacterInSpecificSeries(seriesID: Int) = wrapperWithState {
        Api.marvelService.getCharacterInSpecificSeries(seriesID = seriesID)
    }

    fun getSeriesDetailsWithCharacters(seriesID: Int): Single<State<SeriesDetails>> {
        return getSeriesDetails(seriesID)
            .zipWith(getCharacterInSpecificSeries(seriesID))
            { seriesDetailsResponse: State<APIResponse>,
              characterResponse: State<APIResponse> ->
                combineResult(seriesDetailsResponse, characterResponse)
            }
    }

    private fun combineResult(
        seriesDetailsResponse: State<APIResponse>,
        charactersResponse: State<APIResponse>
    ): State<SeriesDetails> {
        return if (seriesDetailsResponse is State.Success
            && charactersResponse is State.Success
        ) {
            State.Success(seriesDetailsResponse.data?.dataResponse?.itemsResponse?.let {
                charactersResponse.data?.dataResponse?.itemsResponse?.let { it1 ->
                    SeriesDetails(
                        it[0],
                        it1
                    )
                }
            })
        } else {
            State.Failure("Combination error")
        }
    }


    private fun <T> wrapperWithState(function: () -> Single<Response<T>>): Single<State<T>> {
        return function().map {
            if (it.isSuccessful) {
                State.Success(it.body())
            } else {
                State.Failure(it.message())
            }
        }
    }

}
