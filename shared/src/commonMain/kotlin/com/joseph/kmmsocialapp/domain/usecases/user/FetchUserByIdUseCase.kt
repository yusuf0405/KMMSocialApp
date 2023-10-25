package com.joseph.kmmsocialapp.domain.usecases.user

import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.domain.models.UserDetailDomain
import com.joseph.kmmsocialapp.domain.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchUserByIdUseCase : KoinComponent {

    private val repository by inject<UserRepository>()

    suspend operator fun invoke(userId: Int): Result<UserDetailDomain> {
        return repository.fetchUserById(userId)
    }
}