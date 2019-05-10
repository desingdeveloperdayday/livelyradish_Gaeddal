package com.hyeyeon2371.gaeddal.mypage

import com.hyeyeon2371.gaeddal.common.BaseObservableViewModel
import com.hyeyeon2371.gaeddal.common.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.common.util.SharedPrefersUtil

class MypageViewModel(private val navigator: BaseActivityNavigator) : BaseObservableViewModel() {

    fun onClickLogout(){
        SharedPrefersUtil.removeValue(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.LOGGED_IN_USER)
        SharedPrefersUtil.removeValue(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.IS_LOGGED_IN)
        navigator.finishActivity()
    }

}