package com.joseph.kmmsocialapp.data.service

import com.joseph.kmmsocialapp.common.data.KtorApi
import com.joseph.kmmsocialapp.data.models.AuthResponse
import com.joseph.kmmsocialapp.data.models.SignInRequest
import com.joseph.kmmsocialapp.data.models.SignUpRequest
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class AuthService : KtorApi() {

    suspend fun signUp(request: SignUpRequest): AuthResponse = client.post {
        endPoint(path = "/signup")
        setBody(request)
    }.body()

    suspend fun signIn(request: SignInRequest): AuthResponse = client.post {
        endPoint(path = "/login")
        setBody(request)
    }.body()
}