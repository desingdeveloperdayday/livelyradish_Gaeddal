package com.hyeyeon2371.gaeddal.common.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: Any)
}