package com.hyeyeon2371.gaeddal

import android.app.Application
import com.hyeyeon2371.gaeddal.common.kakao.KakaoSDKAdpater
import com.hyeyeon2371.gaeddal.module.appModule
import com.kakao.auth.*
import org.koin.core.context.startKoin

open class App : Application(){
    private lateinit var globalApplication: App

    override fun onCreate() {
        super.onCreate()

        globalApplication = this

        startKoin{
            modules(appModule)
        }

        KakaoSDK.init(KakaoSDKAdpater(globalApplication))
    }

}