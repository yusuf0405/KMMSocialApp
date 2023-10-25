package com.joseph.kmmsocialapp.di

import com.joseph.kmmsocialapp.common.util.coroutines.provideDispatcher
import com.joseph.kmmsocialapp.data.mappers.AuthResponseDataToAuthResultDataMapper
import com.joseph.kmmsocialapp.data.mappers.PostCloudToPostDomainMapper
import com.joseph.kmmsocialapp.data.mappers.UserDetailCloudToUserDetailDomainMapper
import com.joseph.kmmsocialapp.data.mappers.UserInfoCloudToUserInfoDomainMapper
import com.joseph.kmmsocialapp.data.repository.AuthRepositoryImpl
import com.joseph.kmmsocialapp.data.repository.PostRepositoryImpl
import com.joseph.kmmsocialapp.data.repository.UserRepositoryImpl
import com.joseph.kmmsocialapp.data.service.AuthService
import com.joseph.kmmsocialapp.data.service.PostService
import com.joseph.kmmsocialapp.data.service.UserService
import com.joseph.kmmsocialapp.domain.repository.AuthRepository
import com.joseph.kmmsocialapp.domain.repository.PostRepository
import com.joseph.kmmsocialapp.domain.repository.UserRepository
import com.joseph.kmmsocialapp.domain.usecases.onboarding.FetchOnboardingUsersUseCase
import com.joseph.kmmsocialapp.domain.usecases.post.AddPostUseCase
import com.joseph.kmmsocialapp.domain.usecases.post.FetchUserPostsUseCase
import com.joseph.kmmsocialapp.domain.usecases.signin.SignInUseCase
import com.joseph.kmmsocialapp.domain.usecases.signup.SignUpUseCase
import com.joseph.kmmsocialapp.domain.usecases.user.FetchUserByIdUseCase
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory { AuthResponseDataToAuthResultDataMapper() }
    factory { AuthService() }
    factory { SignInUseCase() }
    factory { SignUpUseCase() }
}

private val postModule = module {
    single<PostRepository> { PostRepositoryImpl(get(), get(), get()) }
    factory { PostCloudToPostDomainMapper() }
    factory { PostService() }
    factory { AddPostUseCase() }
    factory { FetchUserPostsUseCase() }
}

private val usersModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    factory { UserInfoCloudToUserInfoDomainMapper() }
    factory { FetchUserByIdUseCase() }
    factory { UserDetailCloudToUserDetailDomainMapper() }
    factory { UserService() }
    factory { FetchOnboardingUsersUseCase() }
}


private val factoryModule = module {
    factory { provideDispatcher() }
}

fun getSharedModule() = listOf(authModule, postModule, usersModule, factoryModule)