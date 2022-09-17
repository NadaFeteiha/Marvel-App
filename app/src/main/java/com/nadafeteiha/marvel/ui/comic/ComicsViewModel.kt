package com.nadafeteiha.marvel.ui.comic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nadafeteiha.marvel.data.network.Repository
import com.nadafeteiha.marvel.data.network.State
import com.nadafeteiha.marvel.data.network.response.APIResponse
import com.nadafeteiha.marvel.data.network.response.ResponseItem
import com.nadafeteiha.marvel.ui.base.BaseViewModel
import com.nadafeteiha.marvel.util.addTo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ComicsViewModel : BaseViewModel() {
    private val repository: Repository by lazy { Repository() }

    private val _comicsState = MutableLiveData<State<APIResponse>>()
    val comicsState: LiveData<State<APIResponse>>
        get() = _comicsState

    private val _comics =
        MutableLiveData<List<ResponseItem>>()
    val comics: LiveData<List<ResponseItem>>
        get() = _comics

    init {
        _comicsState.postValue(State.Loading)
        repository.getComics().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(::onSuccessGetSeries, ::onErrorGetSeries).addTo(disposable)
    }

    private fun onSuccessGetSeries(state: State<APIResponse>) {
        state.toData()?.let {
            _comicsState.postValue(State.Success(it))
            it.dataResponse?.itemsResponse?.let {
                _comics.postValue(it)
            }
        }
    }

    private fun onErrorGetSeries(throwable: Throwable) {
        _comicsState.postValue(State.Failure(throwable.message.toString()))
    }
}