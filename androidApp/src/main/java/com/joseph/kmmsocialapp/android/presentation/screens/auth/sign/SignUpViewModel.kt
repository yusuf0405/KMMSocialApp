package com.joseph.kmmsocialapp.android.presentation.screens.auth.sign

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.kmmsocialapp.android.common.datastore.UserPreferences
import com.joseph.kmmsocialapp.android.mappers.AuthResultDataToUserPreferencesMapper
import com.joseph.kmmsocialapp.domain.usecases.signup.SignUpUseCase
import kotlinx.coroutines.launch
import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.domain.models.AuthResultData

data class SignUpUiState(
    var username: String = String(),
    var lastName: String = String(),
    var email: String = String(),
    var password: String = String(),
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false,
)

class SignUpViewModel(
    private var signUpUseCase: SignUpUseCase,
    private val dataStore: DataStore<UserPreferences>,
    private val authResultDataToUserPreferencesMapper: AuthResultDataToUserPreferencesMapper
) : ViewModel() {

    var uiState by mutableStateOf(SignUpUiState())

    fun signUp() {
        viewModelScope.launch {
            updateUiState(uiState.copy(isAuthenticating = true))
            when (val authResultData = startSignUp()) {
                is Result.Success -> handleSignUpSuccessResult(authResultData.data ?: return@launch)
                is Result.Error -> handleSignUpErrorResult(authResultData.message)
            }
        }
    }

    private suspend fun startSignUp() = signUpUseCase(
        email = uiState.email,
        password = uiState.password,
        name = uiState.username,
        lastName = uiState.lastName
    )

    private suspend fun handleSignUpSuccessResult(authResultData: AuthResultData) {
        dataStore.updateData {
            authResultDataToUserPreferencesMapper.map(authResultData)
        }

        updateUiState(
            uiState.copy(
                isAuthenticating = false,
                authenticationSucceed = true,
            )
        )
    }

    private fun handleSignUpErrorResult(message: String?) {
        updateUiState(
            uiState.copy(
                isAuthenticating = false,
                authErrorMessage = message
            )
        )
    }

    fun updateUsername(input: String) {
        updateUiState(uiState.copy(username = input))
    }

    fun updateUserLastName(input: String) {
        updateUiState(uiState.copy(lastName = input))
    }

    fun updateEmail(input: String) {
        updateUiState(uiState.copy(email = input))
    }

    fun updatePassword(input: String) {
        updateUiState(uiState.copy(password = input))
    }

    private fun updateUiState(uiState: SignUpUiState) {
        this.uiState = uiState
    }

}