package com.hyeyeon2371.gaeddal.mypage.settingmessage.list

import android.databinding.Bindable
import android.databinding.ObservableField
import com.android.databinding.library.baseAdapters.BR
import com.hyeyeon2371.gaeddal.common.base.BaseObservableViewModel

class SettingMessageViewModel(private val navigator: SettingMessageActivityNavigator) : BaseObservableViewModel() {
    val toolbarTitle = "알림 문구 설정"
    var selectedMessagePos = -1

    var list: ObservableField<MutableList<String>> = ObservableField(ArrayList())
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.list)
        }

    init {
        getMessages()
    }

    private fun getMessages() {
        // call setting messages
        list = ObservableField(mutableListOf("msg1", "msg2", "msg3", "msg4", "msg5", "msg6", "msg7", "msg8", "msg9"))
    }

    fun onClickMessage(position: Int) {
        selectedMessagePos = position
        navigator.redirectEditMessageActivity()
    }
}