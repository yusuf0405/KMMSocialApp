package com.joseph.kmmsocialapp.android.home.onboarding

import com.joseph.kmmsocialapp.android.common.fake_data.FollowsUser

data class OnBoardingUiState(
    val isLoading: Boolean = false,
    val users: List<FollowsUser> = emptyList(),
    val errorMessage: String? = null,
    val shouldShowOnBoarding: Boolean = false
)