package com.joseph.kmmsocialapp.android.presentation.screens.profile

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joseph.kmmsocialapp.android.common.fake_data.sampleUserDetail
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Destination
@Composable
fun Profile(
    userId: Int
) {
    val viewModel: ProfileViewModel = koinViewModel(
        parameters = { parametersOf(userId) }
    )

    ProfileScreen(
        uiState = viewModel.profileUiStateFlow.collectAsStateWithLifecycle().value
    )
}