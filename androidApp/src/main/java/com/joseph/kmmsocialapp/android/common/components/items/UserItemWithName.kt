package com.joseph.kmmsocialapp.android.common.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joseph.kmmsocialapp.android.common.components.CircularImage
import com.joseph.kmmsocialapp.android.common.fake_data.FollowsUser
import com.joseph.kmmsocialapp.android.common.fake_data.sampleUsers
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme

@Composable
fun PinnedUserList(
    pinnedUsers: List<FollowsUser>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(ExtraLargeSpacing),
        horizontalArrangement = Arrangement.spacedBy(ExtraLargeSpacing + MediumSpacing)
    ) {
        items(
            items = pinnedUsers
        ) { user ->
            UserItemWithName(
                user = user,
                imageSize = 48.dp
            )
        }
    }
}

@Composable
fun UserItemWithName(
    user: FollowsUser,
    imageSize: Dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularImage(
            modifier = Modifier.size(imageSize),
            imageUrl = user.profileUrl,
        )
        Spacer(modifier = Modifier.height(MediumSpacing))
        Text(
            text = user.name,
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview
@Composable
fun PinnedUserItemPreview() {
    SocialAppTheme {
        UserItemWithName(
            user = sampleUsers.first(),
            imageSize = 48.dp
        )
    }
}


@Preview
@Composable
fun PinnedUserListPreview() {
    SocialAppTheme {
        PinnedUserList(pinnedUsers = sampleUsers)
    }
}