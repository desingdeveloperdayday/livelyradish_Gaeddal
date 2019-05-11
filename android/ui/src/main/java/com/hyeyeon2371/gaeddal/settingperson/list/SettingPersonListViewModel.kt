package com.hyeyeon2371.gaeddal.settingperson.list

import android.databinding.Bindable
import android.databinding.ObservableBoolean
import com.android.databinding.library.baseAdapters.BR
import com.hyeyeon2371.gaeddal.common.base.BaseObservableViewModel

class SettingPersonListViewModel : BaseObservableViewModel() {
    val toolbarTitle = "리스트"
    var itemSelected: ObservableBoolean = ObservableBoolean(true)
        @Bindable
        get() = field

    fun onClickCancel() {
        setItemSelected()
    }

    fun onClickBack() {
        setItemSelected()
    }

    fun onClickDisconnect() {

    }

    fun isItemSelected(): Boolean = itemSelected.get()

    // TEMP
    private fun setItemSelected() {
        itemSelected = ObservableBoolean(!itemSelected.get())
        notifyPropertyChanged(BR.itemSelected)
    }

}