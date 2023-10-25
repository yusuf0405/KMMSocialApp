package com.joseph.kmmsocialapp.android.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination(start = true)
@Composable
fun Home(
    navigator: DestinationsNavigator,
) {
    val viewModel: HomeViewModel = koinViewModel()

    val destination by viewModel.navCommandFlow.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(key1 = destination) {
        if (destination != null) {
            navigator.navigate(destination!!)
        }
    }
    HomeScreen(
        postsUiState = viewModel.postsUiStateFlow.collectAsStateWithLifecycle().value,
        onBoardingUiState = viewModel.onBoardingUiStateFlow.collectAsStateWithLifecycle().value,
        fetchMoreData = viewModel::fetchMoreData,
        actions = viewModel,
    )
}