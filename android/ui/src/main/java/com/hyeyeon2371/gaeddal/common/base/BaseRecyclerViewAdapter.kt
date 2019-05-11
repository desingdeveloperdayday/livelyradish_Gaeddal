package com.hyeyeon2371.gaeddal.common.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseRecyclerViewAdapter<T : ViewDataBinding, S : Any> : RecyclerView.Adapter<BaseViewHolder>() {
    private var items: MutableList<S> = ArrayList()
    lateinit var binding: T

    override fun onCreateViewHolder(parent: ViewGroup, @LayoutRes layout: Int): BaseViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false)
        return viewHolder(layout, binding.root)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayout()
    }

    fun setItems(items: MutableList<S>) {
        this.items = items
        notifyItemRangeChanged(0, itemCount)
    }

    abstract fun getLayout(): Int
    abstract fun viewHolder(@LayoutRes layout: Int, view: View): BaseViewHolder

}