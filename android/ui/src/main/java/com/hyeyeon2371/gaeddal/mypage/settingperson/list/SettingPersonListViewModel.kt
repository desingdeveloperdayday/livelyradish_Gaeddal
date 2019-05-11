package com.hyeyeon2371.gaeddal.mypage.settingperson.list

import android.databinding.Bindable
import android.databinding.ObservableBoolean
import com.android.databinding.library.baseAdapters.BR
import com.hyeyeon2371.gaeddal.common.base.BaseObservableViewModel
import com.hyeyeon2371.gaeddal.data.entity.User

class SettingPersonListViewModel(private val navigator: SettingPersonListActivityNavigator) :
    BaseObservableViewModel() {
    var selectedItemPos: Int = -1
    val toolbarTitle = "리스트"
    var list: MutableList<User> = ArrayList()
    var itemSelected: ObservableBoolean = ObservableBoolean(false)
        @Bindable
        get() = field

    init {
        getPersonList()
    }

    private fun getPersonList() {
        // call person list
        for (i in 0..8) {
            User().apply {
                name = "USER$i"
                profileUrl = "https://t1.daumcdn.net/cfile/tistory/997036485C164CB824"
            }.let {
                list.add(it)
            }
        }
    }

    fun onClickCancel() {
        itemSelected = ObservableBoolean(false)
        notifyPropertyChanged(BR.itemSelected)
    }

    fun onClickBack() {
        navigator.finishActivity()
    }

    fun onClickDisconnect() {
        list.removeAt(selectedItemPos)
        navigator.refreshList()
        navigator.showMessage("컨넥팅을 끊었습니다.")
        itemSelected = ObservableBoolean(false)
        notifyPropertyChanged(BR.itemSelected)
    }

    fun isItemSelected(): Boolean = itemSelected.get()

    fun setItemSelected() {
        if(!itemSelected.get()){
            itemSelected = ObservableBoolean(true)
            notifyPropertyChanged(BR.itemSelected)

        }
    }

}