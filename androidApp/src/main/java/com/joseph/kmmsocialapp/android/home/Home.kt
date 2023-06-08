package com.joseph.kmmsocialapp.android.home

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


@Destination(start = true)
@Composable
fun Home(
    navigator: DestinationsNavigator,
) {
    val viewModel: HomeViewModel = koinViewModel()
    HomeScreen()
}