package com.joseph.kmmsocialapp.domain.repository

import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.domain.models.UserDetailDomain
import com.joseph.kmmsocialapp.domain.models.UserInfoDomain

interface UserRepository {

    suspend fun fetchOnboardingUsers(userId: Int): Result<List<UserInfoDomain>>

    suspend fun fetchUserById(userId: Int): Result<UserDetailDomain>
}