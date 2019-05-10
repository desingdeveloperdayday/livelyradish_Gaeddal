package com.hyeyeon2371.gaeddal.mypage.changename

import android.databinding.Bindable
import com.hyeyeon2371.gaeddal.BR
import com.hyeyeon2371.gaeddal.common.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.common.BaseObservableViewModel

class ChangeNameViewModel(private val navigator: BaseActivityNavigator) : BaseObservableViewModel() {
    var name: String = ""
        @Bindable
        get() = field

    fun onClickSave() {
        if (name.isBlank()) {
            navigator.showMessage("이름을 입력하세요")
        } else {
            navigator.finishActivity()
        }
    }

    fun onClickClear() {
        name = ""
        notifyPropertyChanged(BR.name)
    }
}