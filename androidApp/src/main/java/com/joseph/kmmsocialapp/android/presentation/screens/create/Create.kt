package com.joseph.kmmsocialapp.android.presentation.screens.create

import androidx.compose.runtime.Composable
import com.joseph.kmmsocialapp.android.presentation.screens.home.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun Create() {
    val viewModel: CreateViewModel = koinViewModel()

    CreateScreen(
        actions = viewModel,
        uiState = viewModel.uiState
    )
}