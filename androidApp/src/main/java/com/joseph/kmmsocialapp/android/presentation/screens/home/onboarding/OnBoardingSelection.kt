package com.joseph.kmmsocialapp.android.presentation.screens.home.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.models.UserInfo

@Composable
fun OnBoardingSelection(
    modifier: Modifier = Modifier,
    users: List<UserInfo>,
    onUserClick: (UserInfo) -> Unit,
    onFollowButtonClick: (Boolean, UserInfo) -> Unit,
    onBoardingFinish: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider()
        Spacer(modifier = Modifier.height(LargeSpacing))
        Text(
            text = stringResource(id = R.string.onboarding_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MediumSpacing),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.onboarding_description),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LargeSpacing),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.heightIn(LargeSpacing))
        UserRow(
            users = users,
            onUserClick = onUserClick,
            onFollowButtonClick = onFollowButtonClick
        )
        OutlinedButton(
            onClick = onBoardingFinish,
            modifier = Modifier
                .fillMaxWidth(fraction = 0.5f)
                .align(Alignment.CenterHorizontally)
                .padding(vertical = LargeSpacing),
            shape = RoundedCornerShape(percent = 50)
        ) {
            Text(text = stringResource(id = R.string.onboarding_done_button))
        }

    }
}

@Composable
fun UserRow(
    modifier: Modifier = Modifier,
    users: List<UserInfo>,
    onUserClick: (UserInfo) -> Unit,
    onFollowButtonClick: (Boolean, UserInfo) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(LargeSpacing),
        contentPadding = PaddingValues(horizontal = LargeSpacing),
        modifier = modifier
    ) {
        items(
            count = users.size
        ) { position ->
            OnboardingUserItem(
                followUser = users[position],
                onUserClick = onUserClick,
                onFollowButtonClick = onFollowButtonClick
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingSelectionPreview() {
    SocialAppTheme {
        OnBoardingSelection(
            users = listOf(UserInfo.preview, UserInfo.preview),
            onUserClick = {},
            onFollowButtonClick = { _, _ -> },
            onBoardingFinish = {},
            modifier = Modifier.background(MaterialTheme.colors.onBackground)
        )
    }
}

@Preview
@Composable
fun UserRowPreview() {
    SocialAppTheme {
        UserRow(
            users = listOf(UserInfo.preview, UserInfo.preview),
            onUserClick = {},
            onFollowButtonClick = { _, _ -> },
            modifier = Modifier.background(MaterialTheme.colors.onBackground)
        )
    }
}
