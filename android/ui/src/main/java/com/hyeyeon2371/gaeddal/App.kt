package com.hyeyeon2371.gaeddal

import android.app.Application
import com.hyeyeon2371.gaeddal.module.appModule
import org.koin.core.context.startKoin

open class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appModule)
        }
    }
}