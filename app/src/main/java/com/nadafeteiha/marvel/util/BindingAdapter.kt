package com.nadafeteiha.marvel.util

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.nadafeteiha.marvel.R
import com.nadafeteiha.marvel.data.network.State
import com.nadafeteiha.marvel.data.network.response.Thumbnail
import com.nadafeteiha.marvel.ui.base.BaseAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:textVisibility")
fun setTextVisibility(textView: TextView, visible: Boolean) {
    textView.isVisible = visible
}

@BindingAdapter("app:emptyTextVisibility")
fun setTextVisibilityInCaseEmptyText(textView: TextView, value: String?) {
    textView.isVisible = !value.isNullOrEmpty()
}

@BindingAdapter("app:isSuccess")
fun <T> showWhenSuccess(view: View, state: State<T>?) {
    view.isVisible = state is State.Success
}

@BindingAdapter("app:isNoData")
fun <T> showNoData(view: View, state: State<T>?) {
    if (state is State.Loading ||
        state is State.Failure
    ) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("app:setAnimation")
fun <T> showAnimation(view: LottieAnimationView, state: State<T>?) {
    if (state is State.Loading) {
        view.setAnimation(R.raw.marvel_loading)
    } else {
        view.setAnimation(R.raw.marvel_no_internet)
    }
}


@BindingAdapter("app:image")
fun showImage(imageView: ImageView, thumbnail: Thumbnail?) {
    thumbnail?.let {
        Picasso.with(imageView.context)
            .load(thumbnail.convertToURL())
            .error(R.mipmap.ic_launcher_adaptive_fore)
            .placeholder(R.drawable.loading_animation)
            .into(imageView)
    }
}


@BindingAdapter("app:textWithHtml")
fun showTextHtml(textView: TextView, value: String?) {
    value?.let {
        textView.text = Html.fromHtml(it)
    }
}


@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    if (items != null) {
        (view.adapter as BaseAdapter<T>?)?.setItems(items)
    } else {
        (view.adapter as BaseAdapter<T>?)?.setItems(emptyList())
    }
}