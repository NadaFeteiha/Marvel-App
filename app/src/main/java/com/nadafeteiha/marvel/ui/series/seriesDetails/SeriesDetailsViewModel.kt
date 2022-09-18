package com.nadafeteiha.marvel.ui.series.seriesDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nadafeteiha.marvel.data.SeriesDetails
import com.nadafeteiha.marvel.data.network.Repository
import com.nadafeteiha.marvel.data.network.State
import com.nadafeteiha.marvel.data.network.response.APIResponse
import com.nadafeteiha.marvel.ui.base.BaseViewModel
import com.nadafeteiha.marvel.util.Event
import com.nadafeteiha.marvel.util.addTo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SeriesDetailsViewModel : BaseViewModel(), SeriesCharacterInteractionListener {

    private val repository: Repository by lazy { Repository() }

    private val _seriesDetailsState = MutableLiveData<State<SeriesDetails>>()
    val seriesDetailsState: LiveData<State<SeriesDetails>>
        get() = _seriesDetailsState

    private val _details = MutableLiveData<SeriesDetails>()
    val details: LiveData<SeriesDetails>
        get() = _details

    private val _navigateToCharactersDetails = MutableLiveData(Event(-1))
    val navigateToCharactersDetails: LiveData<Event<Int>>
        get() = _navigateToCharactersDetails

    private var seriesID: Int? = null

    init {
        _seriesDetailsState.postValue(State.Loading)
    }

    fun setSeriesID(seriesID: Int) {
        this.seriesID = seriesID
        callApi()
    }

    private fun callApi() {
        seriesID?.let {
            repository.getSeriesDetailsWithCharacters(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(::onSuccessGetSeries, ::onErrorGetSeries).addTo(disposable)
        }
    }

    private fun onSuccessGetSeries(state: State<SeriesDetails>) {
        state.toData()?.let {
            _seriesDetailsState.postValue(State.Success(it))
            _details.postValue(it)
        }
    }

    private fun onErrorGetSeries(throwable: Throwable) {
        _seriesDetailsState.postValue(State.Failure(throwable.message.toString()))
    }

    override fun onClickSeries(characterID: Int) {
        _navigateToCharactersDetails.postValue(Event(characterID))
    }

    fun retryCallAPI() {
        _seriesDetailsState.postValue(State.Loading)
        callApi()
    }
}