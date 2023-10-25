package com.joseph.kmmsocialapp.android.common.components.bars

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.animation.AnimateSlideTop
import com.joseph.kmmsocialapp.android.common.components.items.UserItemWithName
import com.joseph.kmmsocialapp.android.common.fake_data.sampleUsers
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumElevation
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.ChatDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.ChatListDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.CreateDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.HomeDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.LoginDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.NotificationDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.PostDetailDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.ProfileDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.SearchDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.SignUpDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.StoriesDestination
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@Composable
fun AppTopBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val currentDestination = navHostController.currentDestinationAsState().value
    val isShowNavigationIcon = shouldShowNavigationIcon(currentDestination?.route)
    val isTrailingIconVisible = shouldShowTrailingIcon(currentDestination?.route)

    Surface(
        elevation = MediumElevation,
        color = MaterialTheme.colors.background
    ) {
        AnimateSlideTop(
            isVisible = currentDestination?.route != StoriesDestination.route &&
                    currentDestination?.route != ProfileDestination.route &&
                    currentDestination?.route != CreateDestination.route
        ) {
            Row(
                modifier = modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(horizontal = ExtraLargeSpacing)
                    .padding(vertical = LargeSpacing)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppBarIcon(
                    iconId = R.drawable.arrow_left_icon,
                    onClick = navHostController::navigateUp,
                    isVisible = isShowNavigationIcon
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (currentDestination?.route == ChatDestination.route) {
                        UserItemWithName(
                            user = sampleUsers.first(),
                            imageSize = 32.dp
                        )
                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = getAppBarTitle(currentDestination?.route)),
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center
                    )
                }

                AppBarIcon(
                    iconId = fetchTrailingIcon(currentDestination?.route),
                    isVisible = isTrailingIconVisible,
                    onClick = {
                        when (currentDestination?.route) {
                            HomeDestination.route -> navHostController.navigate(
                                ChatListDestination.route
                            )
                        }
                    }
                )
            }
        }
    }
}


@Composable
private fun AppBarIcon(
    @DrawableRes iconId: Int?,
    onClick: () -> Unit,
    isVisible: Boolean = true
) {
    val isVisibility = isVisible && iconId != null
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = if (isVisibility) MaterialTheme.colors.secondary
                else Color.Transparent
            )
            .size(32.dp)
            .clip(CircleShape)
            .alpha(if (isVisibility) 1f else 0f),
    ) {
        IconButton(
            onClick = onClick,
            enabled = isVisibility
        ) {
            if (iconId != null) Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = iconId),
                contentDescription = null,
            )
        }
    }
}

private fun fetchTrailingIcon(currentDestinationRoute: String?) = when (currentDestinationRoute) {
    HomeDestination.route -> R.drawable.message_icon
    ChatListDestination.route -> R.drawable.settings_icon
    ChatDestination.route -> R.drawable.dots_vertical_icon
    else -> null
}

@StringRes
private fun getAppBarTitle(currentDestinationRoute: String?): Int {
    return when (currentDestinationRoute) {
        LoginDestination.route -> R.string.login_destination_title
        HomeDestination.route -> R.string.home_destination_title
        PostDetailDestination.route -> R.string.post_detail_destination_title
        SignUpDestination.route -> R.string.signup_destination_title
        ChatListDestination.route -> R.string.chat_list_destination_title
        SearchDestination.route -> R.string.search_destination_title
        CreateDestination.route -> R.string.create_destination_title
        NotificationDestination.route -> R.string.notification_destination_title
        ProfileDestination.route -> R.string.profile_destination_title
        else -> R.string.no_destination_title
    }
}

private fun shouldShowNavigationIcon(currentDestinationRoute: String?): Boolean {
    return !(
            currentDestinationRoute == LoginDestination.route ||
                    currentDestinationRoute == SignUpDestination.route ||
                    currentDestinationRoute == HomeDestination.route ||
                    currentDestinationRoute == SearchDestination.route ||
                    currentDestinationRoute == CreateDestination.route ||
                    currentDestinationRoute == NotificationDestination.route ||
                    currentDestinationRoute == ProfileDestination.route ||
                    currentDestinationRoute == StoriesDestination.route
            )
}

private fun shouldShowTrailingIcon(currentDestinationRoute: String?): Boolean {
    return currentDestinationRoute == HomeDestination.route
            || currentDestinationRoute == ChatListDestination.route
            || currentDestinationRoute == ChatDestination.route
}

@Preview
@Composable
fun AppBarPreview(
) {
    SocialAppTheme {
        AppTopBar(
            navHostController = rememberNavController()
        )
    }
}





