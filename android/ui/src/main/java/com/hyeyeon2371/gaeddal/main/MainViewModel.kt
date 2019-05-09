package com.hyeyeon2371.gaeddal.main

import com.hyeyeon2371.gaeddal.common.BaseObservableViewModel
import com.hyeyeon2371.gaeddal.common.CallActivityNavigator

class MainViewModel(private val navigator: CallActivityNavigator) : BaseObservableViewModel() {
    val toolbarTitle = "나늦어"

    fun onClickMypage() = navigator.redirectActivity()
}