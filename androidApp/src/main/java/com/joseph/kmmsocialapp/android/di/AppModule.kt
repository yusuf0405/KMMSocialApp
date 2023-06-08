package com.joseph.kmmsocialapp.android.di

import com.joseph.kmmsocialapp.android.auth.login.LoginViewModel
import com.joseph.kmmsocialapp.android.auth.sign.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { SignUpViewModel() }
//    viewModel { HomeScreenV(get()) }

//    single {
//        DataStoreFactory.create(
//            serializer = UserSettingsSerializer,
//            produceFile = {
//                androidContext().dataStoreFile(fileName = "social-app-prefs")
//            }
//        )
//    }
}