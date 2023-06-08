package com.joseph.kmmsocialapp.android.auth.sign

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class SignUpUiState(
    var username: String = String(),
    var email: String = String(),
    var password: String = String(),
)

class SignUpViewModel(
) : ViewModel() {

    var uiState by mutableStateOf(SignUpUiState())


    fun updateUsername(input: String) {
        updateUiState(uiState.copy(username = input))
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