package com.joseph.kmmsocialapp.data.repository

import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.data.mappers.UserDetailCloudToUserDetailDomainMapper
import com.joseph.kmmsocialapp.data.mappers.UserInfoCloudToUserInfoDomainMapper
import com.joseph.kmmsocialapp.data.service.UserService
import com.joseph.kmmsocialapp.domain.models.UserDetailDomain
import com.joseph.kmmsocialapp.domain.models.UserInfoDomain
import com.joseph.kmmsocialapp.domain.repository.UserRepository

internal class UserRepositoryImpl(
    private val userInfoCloudToUserInfoDomainMapper: UserInfoCloudToUserInfoDomainMapper,
    private val userDetailCloudToUserDetailDomainMapper: UserDetailCloudToUserDetailDomainMapper,
    private val userService: UserService,
) : UserRepository {

    override suspend fun fetchOnboardingUsers(userId: Int): Result<List<UserInfoDomain>> {
        return try {
            val response = userService.fetchOnboardingUsers(userId)
            if (response.data == null) {
                Result.Error(
                    message = response.errorMessage ?: defaultErrorMessage,
                    data = emptyList()
                )
            } else {
                Result.Success(
                    data = response.data.map(userInfoCloudToUserInfoDomainMapper::map)
                )
            }
        } catch (e: Throwable) {
            Result.Error(message = defaultErrorMessage)
        }
    }

    override suspend fun fetchUserById(userId: Int): Result<UserDetailDomain> {
        return try {
            val response = userService.fetchUserById(userId)
            if (response.data == null) {
                Result.Error(
                    message = response.errorMessage ?: defaultErrorMessage,
                )
            } else {
                Result.Success(
                    data = userDetailCloudToUserDetailDomainMapper.map(response.data)
                )
            }
        } catch (e: Throwable) {
            Result.Error(message = defaultErrorMessage)
        }
    }
}