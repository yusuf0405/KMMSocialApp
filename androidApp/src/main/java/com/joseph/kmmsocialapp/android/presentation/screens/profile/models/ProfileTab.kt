package com.joseph.kmmsocialapp.android.presentation.screens.profile.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

data class ProfileTab(
    @StringRes val titleId: Int,
    val content: @Composable () -> Unit
) {
}