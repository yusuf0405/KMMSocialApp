package com.joseph.kmmsocialapp.android.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.joseph.kmmsocialapp.android.MainActivityViewModel
import com.joseph.kmmsocialapp.android.auth.login.LoginViewModel
import com.joseph.kmmsocialapp.android.auth.sign.SignUpViewModel
import com.joseph.kmmsocialapp.android.common.datastore.UserSettingsSerializer
import com.joseph.kmmsocialapp.android.home.HomeViewModel
import com.joseph.kmmsocialapp.android.mappers.AuthResultDataToUserPreferencesMapper
import com.joseph.kmmsocialapp.android.post.PostDetailScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val PREFERENCES_FILE_NAME = "social-app-prefs"
val appModule = module {
    viewModel {
        LoginViewModel(
            signInUseCase = get(),
            authResultDataToUserPreferencesMapper = get(),
            dataStore = get()
        )
    }
    viewModel {
        SignUpViewModel(
            signUpUseCase = get(),
            authResultDataToUserPreferencesMapper = get(),
            dataStore = get()
        )
    }

    viewModel { HomeViewModel() }
    viewModel { PostDetailScreenViewModel() }
    viewModel {
        MainActivityViewModel(
            dataStore = get()
        )
    }
    factory { AuthResultDataToUserPreferencesMapper() }

    single {
        DataStoreFactory.create(
            serializer = UserSettingsSerializer,
            produceFile = {
                androidContext().dataStoreFile(fileName = PREFERENCES_FILE_NAME)
            }
        )
    }
}