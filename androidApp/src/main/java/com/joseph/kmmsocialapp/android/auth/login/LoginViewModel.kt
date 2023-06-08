package com.joseph.kmmsocialapp.android.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class LoginScreenUiState(
    var email: String = String(),
    var password: String = String(),
)

class LoginViewModel(
) : ViewModel() {

    var uiState by mutableStateOf(LoginScreenUiState())

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