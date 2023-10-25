package com.joseph.kmmsocialapp.data.service

import com.joseph.kmmsocialapp.common.data.KtorApi
import com.joseph.kmmsocialapp.data.models.UserInfoListResponse
import com.joseph.kmmsocialapp.data.models.UserDetailResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class UserService : KtorApi() {

    suspend fun fetchOnboardingUsers(userId: Int): UserInfoListResponse = client.get {
        endPoint(path = "/users/onboarding/${userId}")
    }.body()

    suspend fun fetchUserById(userId: Int): UserDetailResponse = client.get {
        endPoint(path = "/users/${userId}")
    }.body()
}