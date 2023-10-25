package com.joseph.kmmsocialapp.domain.usecases.onboarding

import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.domain.models.UserInfoDomain
import com.joseph.kmmsocialapp.domain.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchOnboardingUsersUseCase : KoinComponent {

    private val repository by inject<UserRepository>()

    suspend operator fun invoke(userId: Int): Result<List<UserInfoDomain>> {
        return repository.fetchOnboardingUsers(userId)
    }

}