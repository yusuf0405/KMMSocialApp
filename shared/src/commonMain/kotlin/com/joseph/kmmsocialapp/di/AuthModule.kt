package com.joseph.kmmsocialapp.di

import com.joseph.kmmsocialapp.common.util.provideDispatcher
import com.joseph.kmmsocialapp.data.mappers.AuthResponseDataToAuthResultDataMapper
import com.joseph.kmmsocialapp.data.repository.AuthRepositoryImpl
import com.joseph.kmmsocialapp.data.service.AuthService
import com.joseph.kmmsocialapp.domain.repository.AuthRepository
import com.joseph.kmmsocialapp.domain.usecases.signin.SignInUseCase
import com.joseph.kmmsocialapp.domain.usecases.signup.SignUpUseCase
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory { AuthResponseDataToAuthResultDataMapper() }
    factory { AuthService() }
    factory { SignInUseCase() }
    factory { SignUpUseCase() }
}

private val factoryModule = module {
    factory { provideDispatcher() }
}

fun getSharedModule() = listOf(authModule, factoryModule)