package com.joseph.kmmsocialapp.android.presentation.app

import android.app.Application
import com.joseph.kmmsocialapp.android.di.appModules
import com.joseph.kmmsocialapp.di.getSharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SocialApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SocialApplication)
            modules(appModules() + getSharedModule())
        }
    }
}