package com.joseph.kmmsocialapp.android.auth.sign

import androidx.compose.runtime.Composable
import com.joseph.kmmsocialapp.android.destinations.HomeDestination
import com.joseph.kmmsocialapp.android.destinations.LoginDestination
import com.joseph.kmmsocialapp.android.destinations.SignUpDestination
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