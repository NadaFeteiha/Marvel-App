package com.nadafeteiha.marvel.util

import com.nadafeteiha.marvel.data.network.response.Thumbnail
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun Thumbnail.convertToURL() = this.path + "." + this.extension
