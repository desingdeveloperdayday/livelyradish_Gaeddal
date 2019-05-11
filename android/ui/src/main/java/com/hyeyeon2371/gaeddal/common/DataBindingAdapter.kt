package com.hyeyeon2371.gaeddal.common

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hyeyeon2371.gaeddal.data.entity.User
import com.hyeyeon2371.gaeddal.mypage.settingmessage.list.SettingMessageAdapter
import com.hyeyeon2371.gaeddal.mypage.settingperson.list.SettingPersonListAdapter

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

    @JvmStatic
    @BindingAdapter("bind:settingMsg")
    fun RecyclerView.bindSettingMsg(items: MutableList<String>) =
        (this.adapter as SettingMessageAdapter).setItems(items)

    @JvmStatic
    @BindingAdapter("bind:settingPerson")
    fun RecyclerView.bindSettingPerson(items: MutableList<User>) =
        (this.adapter as SettingPersonListAdapter).setItems(items)
}