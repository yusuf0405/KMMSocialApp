package com.joseph.kmmsocialapp.android.common.components.bars

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.animation.AnimateSlideBottom
import com.joseph.kmmsocialapp.android.common.components.bars.BottomBarScreen.Companion.tabs
import com.joseph.kmmsocialapp.android.common.theme.SmallSpacing
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.CreateDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.HomeDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.NotificationDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.ProfileDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.SearchDestination

@Composable
fun AppBottomBar(
    navController: NavHostController,
    userId: Int
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreenRoute = backStackEntry.value?.destination?.route

    AnimateSlideBottom(
        isVisible = shouldShowBottomBar(currentScreenRoute)
    ) {
        BottomNavigation(
            modifier = Modifier
                .padding(vertical = SmallSpacing),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            tabs(userId).forEach { screen ->
                AddItem(
                    screen = screen,
                    navController = navController,
                    currentScreenRoute = currentScreenRoute
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navController: NavHostController,
    currentScreenRoute: String?,
) {
    BottomNavigationItem(
        icon = {
            if (screen.iconId == R.drawable.create_icon) Image(
                painter = painterResource(id = screen.iconId),
                contentDescription = null,
            )
            else Icon(
                painter = painterResource(id = screen.iconId),
                contentDescription = null,
            )
        },
        selected = screen.route == currentScreenRoute,
        onClick = { navController.navigate(screen.route) },
        selectedContentColor = MaterialTheme.colors.onBackground,
        unselectedContentColor = Color.Gray,
    )
}

data class BottomBarScreen(
    @DrawableRes val iconId: Int,
    val route: String,
) {
    companion object {
        fun tabs(userId: Int) = listOf(
            BottomBarScreen(
                route = HomeDestination.route,
                iconId = R.drawable.feed_icon
            ),
            BottomBarScreen(
                route = SearchDestination.route,
                iconId = R.drawable.search_icon
            ),
            BottomBarScreen(
                route = CreateDestination.route,
                iconId = R.drawable.create_icon
            ),
            BottomBarScreen(
                route = NotificationDestination.route,
                iconId = R.drawable.alert_icon
            ),
            BottomBarScreen(
                route = ProfileDestination(userId).route,
                iconId = R.drawable.profile_icon
            ),
        )
    }
}

private fun shouldShowBottomBar(currentScreenRoute: String?) =
    currentScreenRoute == HomeDestination.route
            || currentScreenRoute == SearchDestination.route
            || currentScreenRoute == CreateDestination.route
            || currentScreenRoute == NotificationDestination.route
            || currentScreenRoute == ProfileDestination.route