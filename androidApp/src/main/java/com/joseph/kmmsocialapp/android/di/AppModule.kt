package com.joseph.kmmsocialapp.android.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.joseph.kmmsocialapp.android.common.datastore.UserDataStore
import com.joseph.kmmsocialapp.android.common.datastore.UserDataStoreImpl
import com.joseph.kmmsocialapp.android.common.datastore.UserSettingsSerializer
import com.joseph.kmmsocialapp.android.common.managers.ToastDisplayFlowManager
import com.joseph.kmmsocialapp.android.common.managers.ToastDisplayManager
import com.joseph.kmmsocialapp.android.common.managers.ToastManager
import com.joseph.kmmsocialapp.android.common.provider.CoroutineDispatcherProvider
import com.joseph.kmmsocialapp.android.common.provider.CoroutineDispatcherProviderImpl
import com.joseph.kmmsocialapp.android.mappers.AuthResultDataToUserPreferencesMapper
import com.joseph.kmmsocialapp.android.mappers.PostDomainToPostMapper
import com.joseph.kmmsocialapp.android.mappers.PostDomainToPostMapperImpl
import com.joseph.kmmsocialapp.android.mappers.UserDetailDomainToUserDetailMapper
import com.joseph.kmmsocialapp.android.mappers.UserInfoDomainToUserInfoMapper
import com.joseph.kmmsocialapp.android.presentation.app.MainActivityViewModel
import com.joseph.kmmsocialapp.android.presentation.screens.auth.login.LoginViewModel
import com.joseph.kmmsocialapp.android.presentation.screens.auth.sign.SignUpViewModel
import com.joseph.kmmsocialapp.android.presentation.screens.create.CreateViewModel
import com.joseph.kmmsocialapp.android.presentation.screens.home.HomeViewModel
import com.joseph.kmmsocialapp.android.presentation.screens.post.PostDetailScreenViewModel
import com.joseph.kmmsocialapp.android.presentation.screens.profile.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val PREFERENCES_FILE_NAME = "social-app-prefs"

fun appModules() = listOf(appModule, toastModule, viewModelsModule)

private val appModule = module {
    factory { AuthResultDataToUserPreferencesMapper() }
    factory<PostDomainToPostMapper> { PostDomainToPostMapperImpl(get()) }
    factory { UserDetailDomainToUserDetailMapper() }
    factory { UserInfoDomainToUserInfoMapper() }

    single<UserDataStore> { UserDataStoreImpl(get()) }
    factory<CoroutineDispatcherProvider> { CoroutineDispatcherProviderImpl() }

    single<ToastDisplayFlowManager> { ToastManager() }
    single<ToastDisplayManager> { ToastManager() }

    single {
        DataStoreFactory.create(
            serializer = UserSettingsSerializer,
            produceFile = {
                androidContext().dataStoreFile(fileName = PREFERENCES_FILE_NAME)
            }
        )
    }
}
private val viewModelsModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { SignUpViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get(), get(), get(), get(), get()) }
    viewModel { CreateViewModel(get(), get(), get(), get(), get()) }
    viewModel { PostDetailScreenViewModel() }
    viewModel { MainActivityViewModel(get(), get()) }
    viewModel { params ->
        ProfileViewModel(
            userId = params.get(), get(), get(), get(), get()
        )
    }
}


private val toastModule = module {
    single { ToastManager() }
    single<ToastDisplayFlowManager> { get<ToastManager>() }
    single<ToastDisplayManager> { get<ToastManager>() }
}