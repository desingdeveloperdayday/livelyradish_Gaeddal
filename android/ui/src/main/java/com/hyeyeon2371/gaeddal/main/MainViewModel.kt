package com.hyeyeon2371.gaeddal.main

import com.hyeyeon2371.gaeddal.common.BaseObservableViewModel

class MainViewModel(private val navigator: MainActivityNavigator) : BaseObservableViewModel() {
    val toolbarTitle = "나늦어"

    fun onClickMypage() = navigator.redirectActivity()
}