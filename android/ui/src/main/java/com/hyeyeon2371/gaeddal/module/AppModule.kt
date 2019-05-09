package com.hyeyeon2371.gaeddal.module

import com.hyeyeon2371.gaeddal.common.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.login.LoginViewModel
import com.hyeyeon2371.gaeddal.main.MainActivityNavigator
import com.hyeyeon2371.gaeddal.main.MainViewModel
import com.hyeyeon2371.gaeddal.mypage.MypageViewModel
import com.hyeyeon2371.gaeddal.settingperson.list.SettingPersonListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SettingPersonListViewModel() }
    viewModel { (navigator : BaseActivityNavigator) -> LoginViewModel(navigator) }
    viewModel { (navigator : MainActivityNavigator) ->  MainViewModel(navigator) }
    viewModel { (navigator : BaseActivityNavigator) ->  MypageViewModel(navigator) }
}
