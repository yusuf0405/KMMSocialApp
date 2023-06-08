package com.joseph.kmmsocialapp.android.auth.sign

import androidx.compose.runtime.Composable
import com.joseph.kmmsocialapp.android.auth.destinations.LoginDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


@Destination(start = true)
@Composable
fun SignUp(
    navigator: DestinationsNavigator,
) {
    val viewModel: SignUpViewModel = koinViewModel()

    SignUpScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onUserNameChange = viewModel::updateUsername,
        onNavigateToLogin = {
            navigator.navigate(LoginDestination)
        }
    )
}