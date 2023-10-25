package com.joseph.kmmsocialapp.android.presentation.screens.profile

import com.joseph.kmmsocialapp.android.models.UserDetail
import com.joseph.kmmsocialapp.android.presentation.screens.profile.models.ProfileTab

data class ProfileUiState(
    val userDetail: UserDetail = UserDetail.unknown,
    val errorMessage: String = String(),
    val isLoading: Boolean = false,
    val tabs: List<ProfileTab> = emptyList(),
    val isCurrentUser: Boolean = false
)