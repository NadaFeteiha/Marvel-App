package com.nadafeteiha.marvel.ui.characters

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

class CharacterViewModel : BaseViewModel() {
    private val repository: Repository by lazy { Repository() }

    private val _characterState = MutableLiveData<State<APIResponse>>()
    val characterState: LiveData<State<APIResponse>>
        get() = _characterState

    private val _character = MutableLiveData<ResponseItem>()
    val character: LiveData<ResponseItem>
        get() = _character

    private val _descriptionVisibility = MutableLiveData(false)
    val descriptionVisibility: LiveData<Boolean>
        get() = _descriptionVisibility

    private var characterID: Int? = null

    init {
        _characterState.postValue(State.Loading)
    }

    fun initCharacter(characterID: Int) {
        this.characterID = characterID
        callCharacterAPI()
    }

    private fun callCharacterAPI() {
        characterID?.let {
            repository.getCharacterByID(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(::onSuccessGetCharacter, ::onErrorGetCharacter).addTo(disposable)
        }
    }

    private fun onSuccessGetCharacter(state: State<APIResponse>) {
        _characterState.postValue(state)
        state.toData()?.let {
            _characterState.postValue(State.Success(it))
            _character.postValue(it.dataResponse?.itemsResponse?.get(0))
            _descriptionVisibility.postValue(it.dataResponse?.itemsResponse?.get(0)?.description?.isNotEmpty())
        }
    }

    private fun onErrorGetCharacter(throwable: Throwable) {
        _characterState.postValue(State.Failure(throwable.message.toString()))
    }

    fun retryCallAPI() {
        _characterState.postValue(State.Loading)
        callCharacterAPI()
    }

}