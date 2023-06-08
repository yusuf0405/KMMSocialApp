package com.joseph.kmmsocialapp.android.common.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.theme.SmallElevation
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.destinations.HomeDestination
import com.joseph.kmmsocialapp.android.destinations.LoginDestination
import com.joseph.kmmsocialapp.android.destinations.SignUpDestination
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@Preview
@Composable
fun AppBarPreview(
) {
    SocialAppTheme {
        AppBar(
            navHostController = rememberNavController()
        )
    }
}

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val currentDestination = navHostController.currentDestinationAsState().value

    Surface(
        modifier = modifier,
        elevation = SmallElevation
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = shouldShowNavigationIcon(currentDestinationRoute = currentDestination?.route)) {
                IconButton(onClick = {
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
                Spacer(modifier = modifier.width(24.dp))
            }

            TopAppBar(
                modifier = modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = stringResource(id = getAppBarTitle(currentDestination?.route)),
                    )
                },
                backgroundColor = MaterialTheme.colors.surface,
                actions = {
                    AnimatedVisibility(
                        visible = currentDestination?.route == HomeDestination.route
                    ) {
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.person_circle_icon),
                                contentDescription = null
                            )
                        }

                    }

                },
            )
        }
    }
}

@StringRes
private fun getAppBarTitle(currentDestinationRoute: String?): Int {
    return when (currentDestinationRoute) {
        LoginDestination.route -> R.string.login_destination_title
        HomeDestination.route -> R.string.app_name
        SignUpDestination.route -> R.string.signup_destination_title
        else -> R.string.no_destination_title
    }
}

private fun shouldShowNavigationIcon(currentDestinationRoute: String?): Boolean {
    return false
}










