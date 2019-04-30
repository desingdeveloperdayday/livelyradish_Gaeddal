package com.hyeyeon2371.gaeddal.module

import com.hyeyeon2371.gaeddal.login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
}