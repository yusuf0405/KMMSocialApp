package com.joseph.kmmsocialapp.android

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.joseph.kmmsocialapp.android.common.components.AppBar
import com.joseph.kmmsocialapp.android.destinations.HomeDestination
import com.joseph.kmmsocialapp.android.destinations.LoginDestination
import com.ramcosta.composedestinations.DestinationsNavHost

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SocialApp(
    token: String?
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

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                navHostController = navHostController
            )
        }
    ) { innerPaddings ->
        DestinationsNavHost(
            modifier = Modifier.padding(innerPaddings),
            navGraph = NavGraphs.root,
            navController = navHostController
        )
    }

    LaunchedEffect(
        key1 = token,
        block = {
            if (token != null && token.isEmpty()) {
                navHostController.navigate(LoginDestination.route) {
                    popUpTo(HomeDestination.route) {
                        inclusive = true
                    }
                }
            }
        }
    )

}