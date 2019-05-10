package com.hyeyeon2371.gaeddal.common

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object DataBindingAdapter {
    @JvmStatic
    @BindingAdapter("bind:loadImg")
    fun ImageView.bindImage(src: Any?) {
        Glide.with(this.context).load(src).thumbnail(0.5f).into(this)
    }

    @JvmStatic
    @BindingAdapter("bind:loadOvalImg")
    fun ImageView.bindUrlWithOval(src: Any?) {
        Glide.with(this.context).load(src).thumbnail(0.5f).apply(RequestOptions.circleCropTransform()).into(this)
    }
}