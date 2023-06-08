package com.joseph.kmmsocialapp.android.auth.login

import androidx.compose.runtime.Composable
import com.joseph.kmmsocialapp.android.destinations.HomeDestination
import com.joseph.kmmsocialapp.android.destinations.LoginDestination
import com.joseph.kmmsocialapp.android.destinations.SignUpDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun Login(
    navigator: DestinationsNavigator,
) {
    val viewModel: LoginViewModel = koinViewModel()

    LoginScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onSignInClick = viewModel::signIn,
        onNavigateToHome = {
            navigator.navigate(HomeDestination) {
                popUpTo(LoginDestination.route) {
                    inclusive = true
                }
            }
        },
        onNavigateToSignUp = { navigator.navigate(SignUpDestination) }
    )
}