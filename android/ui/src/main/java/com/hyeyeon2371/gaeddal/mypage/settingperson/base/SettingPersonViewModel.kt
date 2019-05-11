package com.hyeyeon2371.gaeddal.mypage.settingperson.base

import com.hyeyeon2371.gaeddal.common.base.BaseObservableViewModel

class SettingPersonViewModel(val navigator: SettingPersonActivityNavigator) : BaseObservableViewModel() {

    fun onClickPersonList() {
        navigator.redirectPersonListActivity()
    }

    fun onClickAddPerson() {
        navigator.redirectAddPersonActivity()
    }

    fun onClickRequestList() {
        navigator.redirectRequestPersonActivity()
    }
}