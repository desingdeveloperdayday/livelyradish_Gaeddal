package com.hyeyeon2371.gaeddal.module

import com.hyeyeon2371.gaeddal.common.base.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.login.LoginViewModel
import com.hyeyeon2371.gaeddal.main.MainActivityNavigator
import com.hyeyeon2371.gaeddal.main.MainViewModel
import com.hyeyeon2371.gaeddal.mypage.base.MypageActivityNavigator
import com.hyeyeon2371.gaeddal.mypage.base.MypageViewModel
import com.hyeyeon2371.gaeddal.mypage.settingmessage.edit.EditSettingMessageActivityNavigator
import com.hyeyeon2371.gaeddal.mypage.settingmessage.edit.EditSettingMessageViewModel
import com.hyeyeon2371.gaeddal.mypage.settingmessage.list.SettingMessageActivityNavigator
import com.hyeyeon2371.gaeddal.mypage.settingmessage.list.SettingMessageViewModel
import com.hyeyeon2371.gaeddal.mypage.settingmessage.write.WriteSettingMessageActivityNavigator
import com.hyeyeon2371.gaeddal.mypage.settingmessage.write.WriteSettingMessageViewModel
import com.hyeyeon2371.gaeddal.mypage.settingperson.base.SettingPersonActivityNavigator
import com.hyeyeon2371.gaeddal.mypage.settingperson.base.SettingPersonViewModel
import com.hyeyeon2371.gaeddal.mypage.settingperson.list.SettingPersonListActivityNavigator
import com.hyeyeon2371.gaeddal.mypage.settingperson.list.SettingPersonListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { (navigator: BaseActivityNavigator) -> LoginViewModel(navigator) }
    viewModel { (navigator: MainActivityNavigator) -> MainViewModel(navigator) }
    viewModel { (navigator: MypageActivityNavigator) -> MypageViewModel(navigator) }
    viewModel { (navigator: SettingMessageActivityNavigator) -> SettingMessageViewModel(navigator) }
    viewModel { (navigator: WriteSettingMessageActivityNavigator) -> WriteSettingMessageViewModel(navigator) }
    viewModel { (navigator: EditSettingMessageActivityNavigator) -> EditSettingMessageViewModel(navigator) }
    viewModel { (navigator: SettingPersonActivityNavigator) -> SettingPersonViewModel(navigator) }
    viewModel { (navigator: SettingPersonListActivityNavigator) -> SettingPersonListViewModel(navigator) }
}
