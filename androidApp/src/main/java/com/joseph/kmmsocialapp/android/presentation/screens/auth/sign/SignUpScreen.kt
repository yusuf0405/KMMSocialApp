package com.joseph.kmmsocialapp.android.presentation.screens.auth.sign

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.common.components.CustomButton
import com.joseph.kmmsocialapp.android.common.components.text_filed.LoginTextField

@Preview
@Composable
fun SignUpScreenPreview() {
    SocialAppTheme {
        SignUpScreen(
            uiState = SignUpUiState()
        )
    }
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpUiState,
    onUserNameChange: (String) -> Unit = {},
    onUserLastNameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    color = if (isSystemInDarkTheme()) MaterialTheme.colors.background
                    else MaterialTheme.colors.surface
                )
                .padding(
                    top = ExtraLargeSpacing + LargeSpacing,
                    start = LargeSpacing + MediumSpacing,
                    end = LargeSpacing + MediumSpacing,
                    bottom = LargeSpacing,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            LoginTextField(
                value = uiState.username,
                onValueChange = onUserNameChange,
                hintResId = R.string.username_hint
            )
            LoginTextField(
                value = uiState.lastName,
                onValueChange = onUserLastNameChange,
                hintResId = R.string.last_name_hint
            )
            LoginTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                hintResId = R.string.email_hint,
                keyboardType = KeyboardType.Email
            )
            LoginTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                hintResId = R.string.password_hint,
                keyboardType = KeyboardType.Password,
                isPasswordTextField = true
            )

            CustomButton(
                titleResId = R.string.signup_button_hint,
                onClickAction = { onSignUpClick() }
            )
            GoToLogin(onNavigateToLogin = onNavigateToLogin)
        }

        if (uiState.isAuthenticating) {
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(
        key1 = uiState.authenticationSucceed,
        key2 = uiState.authErrorMessage,
        block = {
            if (uiState.authenticationSucceed) onNavigateToHome()

            if (uiState.authErrorMessage != null) {
                Toast.makeText(
                    context,
                    uiState.authErrorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )
}

@Composable
fun GoToLogin(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {

        Text(
            text = "Already have an account?",
            style = MaterialTheme.typography.caption,

            )
        Text(
            modifier = modifier.clickable { onNavigateToLogin() },
            text = "Login",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary
        )

    }
}