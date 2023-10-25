package com.joseph.kmmsocialapp.domain.usecases.signup

import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.domain.models.AuthResultData
import com.joseph.kmmsocialapp.domain.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpUseCase : KoinComponent {

    private val repository by inject<AuthRepository>()

    suspend operator fun invoke(
        name: String,
        lastName: String,
        email: String,
        password: String,
    ): Result<AuthResultData> {

        if (name.isBlank() || name.length < 3) {
            return Result.Error(
                message = "Invalid name"
            )
        }

        if (lastName.isBlank() || lastName.length < 3) {
            return Result.Error(
                message = "Invalid last name"
            )
        }

        if (email.isBlank() || "@" !in email) {
            return Result.Error(
                message = "Invalid email"
            )
        }

        if (password.isBlank() || password.length < 8) {
            return Result.Error(
                message = "Invalid password"
            )
        }

        return repository.signUp(
            email = email,
            name = name,
            password = password,
            lastName = lastName
        )
    }
}

