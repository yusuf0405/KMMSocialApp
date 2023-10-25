package com.joseph.kmmsocialapp.domain.repository

import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.domain.models.AuthResultData

internal interface AuthRepository {

    suspend fun signUp(
        name: String,
        lastName: String,
        email: String,
        password: String,
    ): Result<AuthResultData>


    suspend fun signIn(
        email: String,
        password: String,
    ): Result<AuthResultData>
}