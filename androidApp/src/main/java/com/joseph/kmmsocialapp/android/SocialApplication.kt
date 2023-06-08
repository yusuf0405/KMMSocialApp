package com.joseph.kmmsocialapp.android

import android.app.Application
import com.joseph.kmmsocialapp.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SocialApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SocialApplication)
            modules(appModule)
        }
    }
}