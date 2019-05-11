package com.hyeyeon2371.gaeddal.common.base

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry

open class BaseObservableViewModel : ViewModel(), Observable, LifecycleObserver {
    private val registry: PropertyChangeRegistry? = PropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry?.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry?.add(callback)
    }

    fun notifyChange() {
        synchronized(this) {
            if (registry == null) {
                return
            }
        }
        registry!!.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId : Int){
        synchronized(this){
            if(registry == null){
                return
            }
        }
        registry!!.notifyCallbacks(this, fieldId, null)
    }



}