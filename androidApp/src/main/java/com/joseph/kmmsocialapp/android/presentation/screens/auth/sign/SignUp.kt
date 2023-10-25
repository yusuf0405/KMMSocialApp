package com.joseph.kmmsocialapp.android.presentation.screens.auth.sign

import androidx.compose.runtime.Composable
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.HomeDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.LoginDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.SignUpDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


@Destination
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
        onUserLastNameChange = viewModel::updateUserLastName,
        onSignUpClick = viewModel::signUp,
        onNavigateToLogin = { navigator.navigate(LoginDestination) },
        onNavigateToHome = {
            navigator.navigate(HomeDestination) {
                popUpTo(SignUpDestination.route) {
                    inclusive = true
                }
            }
        }
    )
}