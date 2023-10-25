package com.joseph.kmmsocialapp.android.presentation.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.joseph.kmmsocialapp.android.common.components.bars.AppBottomBar
import com.joseph.kmmsocialapp.android.common.components.bars.AppTopBar
import com.joseph.kmmsocialapp.android.presentation.screens.NavGraphs
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.HomeDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.LoginDestination
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun SocialApp(
    loginOutState: State<Boolean>,
    userId: Int?,
) {
    val navHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val systemUiController = rememberSystemUiController()
    val isSystemInDark = isSystemInDarkTheme()

    val statusBarColor = MaterialTheme.colors.surface

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = !isSystemInDark
        )
    }

    if (userId != null) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppTopBar(navHostController) },
            bottomBar = { AppBottomBar(navHostController, userId) }

        ) { innerPaddings ->
            DestinationsNavHost(
                modifier = Modifier.padding(innerPaddings),
                navGraph = NavGraphs.root,
                navController = navHostController
            )
        }
    }

    LaunchedEffect(
        key1 = loginOutState.value,
        block = {
            if (loginOutState.value) {
                navHostController.navigate(LoginDestination.route) {
                    popUpTo(HomeDestination.route) {
                        inclusive = true
                    }
                }
            }
        }
    )
}





