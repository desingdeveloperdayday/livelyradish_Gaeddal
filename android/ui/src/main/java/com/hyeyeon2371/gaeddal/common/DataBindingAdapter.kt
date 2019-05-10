package com.hyeyeon2371.gaeddal.common

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

object DataBindingAdapter {
    @JvmStatic
    @BindingAdapter("bind:loadImg")
    fun ImageView.bindImage(src: Any?) {
        Glide.with(this.context).load(src).thumbnail(0.5f).into(this)
    }
}