package com.joseph.kmmsocialapp.data.repository

import com.joseph.kmmsocialapp.common.util.DispatcherProvider
import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.data.mappers.AuthResponseDataToAuthResultDataMapper
import com.joseph.kmmsocialapp.data.models.SignInRequest
import com.joseph.kmmsocialapp.data.models.SignUpRequest
import com.joseph.kmmsocialapp.data.service.AuthService
import com.joseph.kmmsocialapp.domain.models.AuthResultData
import com.joseph.kmmsocialapp.domain.repository.AuthRepository
import kotlinx.coroutines.withContext

internal class AuthRepositoryImpl(
    private val authService: AuthService,
    private val dispatcherProvider: DispatcherProvider,
    private val authResponseDataToAuthResultDataMapper: AuthResponseDataToAuthResultDataMapper
) : AuthRepository {

    private val defaultErrorMessage = "Oops, we could not send your request, try later!"

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData> {
        return withContext(dispatcherProvider.io) {
            try {
                val request = SignUpRequest(
                    email = email,
                    password = password,
                    name = name
                )
                val authResponse = authService.signUp(request)
                if (authResponse.data == null) {
                    Result.Error(
                        message = authResponse.errorMessage ?: defaultErrorMessage
                    )
                } else {
                    Result.Success(
                        data = authResponseDataToAuthResultDataMapper.map(authResponse.data)
                    )
                }

            } catch (e: Exception) {
                Result.Error(
                    message = defaultErrorMessage
                )
            }
        }

    }

    override suspend fun signIn(email: String, password: String): Result<AuthResultData> {
        return withContext(dispatcherProvider.io) {
            try {
                val request = SignInRequest(
                    email = email,
                    password = password,
                )
                val authResponse = authService.signIn(request)
                if (authResponse.data == null) {
                    Result.Error(
                        message = authResponse.errorMessage ?: defaultErrorMessage
                    )
                } else {
                    Result.Success(
                        data = authResponseDataToAuthResultDataMapper.map(authResponse.data)
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = defaultErrorMessage
                )
            }
        }
    }
}