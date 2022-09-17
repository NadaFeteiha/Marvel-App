package com.nadafeteiha.marvel.ui.series

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nadafeteiha.marvel.data.network.Repository
import com.nadafeteiha.marvel.data.network.State
import com.nadafeteiha.marvel.data.network.response.APIResponse
import com.nadafeteiha.marvel.ui.base.BaseViewModel
import com.nadafeteiha.marvel.util.Event
import com.nadafeteiha.marvel.util.addTo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SeriesViewModel : BaseViewModel(), SeriesInteractionListener {

    private val repository: Repository by lazy { Repository() }

    private val _seriesState = MutableLiveData<State<APIResponse>>()
    val seriesState: LiveData<State<APIResponse>>
        get() = _seriesState

    private val _series =
        MutableLiveData<List<com.nadafeteiha.marvel.data.network.response.ResponseItem>>()
    val series: LiveData<List<com.nadafeteiha.marvel.data.network.response.ResponseItem>>
        get() = _series

    private val _navigateToComics = MutableLiveData<Event<Boolean>>()
    val navigateToComics: LiveData<Event<Boolean>>
        get() = _navigateToComics

    private val _navigateToSeriesDetails = MutableLiveData(Event(-1))
    val navigateToSeriesDetails: LiveData<Event<Int>>
        get() = _navigateToSeriesDetails

    init {
        _seriesState.postValue(State.Loading)
        repository.getSeries().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(::onSuccessGetSeries, ::onErrorGetSeries).addTo(disposable)
    }


    private fun onSuccessGetSeries(state: State<APIResponse>) {
        state.toData()?.let {
            _seriesState.postValue(State.Success(it))
            it.dataResponse?.let {
                _series.postValue(it.itemsResponse!!)
            }
        }
    }

    private fun onErrorGetSeries(throwable: Throwable) {
        _seriesState.postValue(State.Failure(throwable.message.toString()))
    }

    fun navigateToComics() {
        _navigateToComics.postValue(Event(true))
    }

    override fun onClickSeries(seriesID: Int) {
        _navigateToSeriesDetails.postValue(Event(seriesID))
    }
}