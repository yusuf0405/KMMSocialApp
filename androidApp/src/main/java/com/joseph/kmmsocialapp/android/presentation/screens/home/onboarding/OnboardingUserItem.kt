package com.joseph.kmmsocialapp.android.presentation.screens.home.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.components.CircularImage
import com.joseph.kmmsocialapp.android.common.components.FollowButton
import com.joseph.kmmsocialapp.android.common.extensions.clickableNoRipple
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SmallSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.models.UserInfo

@Composable
fun OnboardingUserItem(
    followUser: UserInfo,
    onUserClick: (UserInfo) -> Unit,
    isFollowing: Boolean = false,
    onFollowButtonClick: (Boolean, UserInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(top = SmallSpacing)
            .height(140.dp)
            .width(130.dp)
            .clickableNoRipple { onUserClick(followUser) },
        elevation = 2.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(MediumSpacing),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularImage(
                modifier = modifier.size(50.dp),
                imageUrl = followUser.avatar,
                placeHolder = painterResource(id = R.drawable.user_placeholder)
            ) {
                onUserClick(followUser)
            }
            Spacer(modifier = modifier.height(SmallSpacing))
            Text(
                text = followUser.name,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = modifier.height(MediumSpacing))
            FollowButton(
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(30.dp),
                onClick = {
                    onFollowButtonClick(!isFollowing, followUser)
                }
            )
        }
    }
}

@Preview
@Composable
fun OnboardingUserItemPreview() {
    SocialAppTheme {
        OnboardingUserItem(
            followUser = UserInfo.preview,
            onUserClick = {},
            onFollowButtonClick = { _, _ -> }
        )
    }
}
