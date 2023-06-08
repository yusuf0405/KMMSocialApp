package com.joseph.kmmsocialapp.android.auth.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.ButtonHeight
import com.joseph.kmmsocialapp.android.common.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.LargeSpacing
import com.joseph.kmmsocialapp.android.common.MediumSpacing
import com.joseph.kmmsocialapp.android.common.SocialAppTheme
import com.joseph.kmmsocialapp.android.common.components.CustomButton
import com.joseph.kmmsocialapp.android.common.components.CustomTextField
import com.ramcosta.composedestinations.annotation.Destination

@Preview
@Composable
fun SignUpScreenPreview() {
    SocialAppTheme {
        SignUpScreen(
            uiState = SignUpUiState()
        )
    }
}

@Destination
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpUiState,
    onUserNameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
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
        CustomTextField(
            value = uiState.username,
            onValueChange = onUserNameChange,
            hintResId = R.string.username_hint
        )
        CustomTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            hintResId = R.string.email_hint,
            keyboardType = KeyboardType.Email
        )
        CustomTextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            hintResId = R.string.password_hint,
            keyboardType = KeyboardType.Password,
            isPasswordTextField = true
        )

        CustomButton(
            titleResId = R.string.signup_button_hint,
            onClickAction = {
                onNavigateToLogin()
            }
        )
    }
}