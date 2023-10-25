package com.joseph.kmmsocialapp.android.presentation.screens.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.kmmsocialapp.android.common.datastore.UserPreferences
import com.joseph.kmmsocialapp.android.mappers.AuthResultDataToUserPreferencesMapper
import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.domain.models.AuthResultData
import com.joseph.kmmsocialapp.domain.usecases.signin.SignInUseCase
import kotlinx.coroutines.launch

data class LoginScreenUiState(
    var email: String = String(),
    var password: String = String(),
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false,
)

class LoginViewModel(
    private val signInUseCase: SignInUseCase,
    private val dataStore: DataStore<UserPreferences>,
    private val authResultDataToUserPreferencesMapper: AuthResultDataToUserPreferencesMapper
) : ViewModel() {

    var uiState by mutableStateOf(LoginScreenUiState())

    fun signIn() {
        viewModelScope.launch {
            updateUiState(uiState.copy(isAuthenticating = true))

            when (val authResultData = startSignIn()) {
                is Result.Success -> handleSuccessSignIn(authResultData.data ?: return@launch)
                is Result.Error -> handleErrorSignIn(authResultData.message)
            }
        }
    }

    private suspend fun startSignIn() = signInUseCase(
        email = uiState.email,
        password = uiState.password,
    )

    private suspend fun handleSuccessSignIn(authResultData: AuthResultData) {
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

    private fun handleErrorSignIn(message: String?) {
        updateUiState(
            uiState.copy(
                isAuthenticating = false,
                authErrorMessage = message
            )
        )
    }

    fun updateEmail(input: String) {
        updateUiState(uiState.copy(email = input))
    }

    fun updatePassword(input: String) {
        updateUiState(uiState.copy(password = input))
    }

    private fun updateUiState(uiState: LoginScreenUiState) {
        this.uiState = uiState
    }
}