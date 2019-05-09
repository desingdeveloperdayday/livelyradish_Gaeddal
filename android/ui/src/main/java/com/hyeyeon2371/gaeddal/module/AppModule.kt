package com.hyeyeon2371.gaeddal.module

import com.hyeyeon2371.gaeddal.common.CallActivityNavigator
import com.hyeyeon2371.gaeddal.login.LoginViewModel
import com.hyeyeon2371.gaeddal.main.MainViewModel
import com.hyeyeon2371.gaeddal.settingperson.list.SettingPersonListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { SettingPersonListViewModel() }
    viewModel { (navigator : CallActivityNavigator) ->  MainViewModel(navigator) }
}
