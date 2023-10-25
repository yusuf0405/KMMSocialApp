package com.joseph.kmmsocialapp.android.presentation.screens.home.onboarding

import com.joseph.kmmsocialapp.android.models.UserInfo

data class OnBoardingUiState(
    val users: List<UserInfo> = emptyList(),
    val errorMessage: String? = null,
    val shouldShowOnBoarding: Boolean = false
)