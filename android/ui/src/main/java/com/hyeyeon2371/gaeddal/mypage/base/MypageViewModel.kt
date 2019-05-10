package com.hyeyeon2371.gaeddal.mypage.base

import android.databinding.Bindable
import com.hyeyeon2371.gaeddal.common.BaseObservableViewModel
import com.hyeyeon2371.gaeddal.common.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.common.util.SharedPrefersUtil
import com.hyeyeon2371.gaeddal.data.entity.User

class MypageViewModel(private val navigator: MypageActivityNavigator) : BaseObservableViewModel() {
    var user: User = User()
        @Bindable
        get() = field

    fun onClickLogout() {
        SharedPrefersUtil.removeValue(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.LOGGED_IN_USER)
        SharedPrefersUtil.removeValue(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.IS_LOGGED_IN)
        navigator.finishActivity()
    }

    fun onClickChangeName() {
        navigator.redirectChangeNameActivity()
    }

}