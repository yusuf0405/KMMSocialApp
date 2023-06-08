package com.joseph.kmmsocialapp.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.joseph.kmmsocialapp.android.auth.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun SocialApp() {
    val navHostController = rememberNavController()

    DestinationsNavHost(
        navGraph = NavGraphs.root,
        navController = navHostController
    )
}