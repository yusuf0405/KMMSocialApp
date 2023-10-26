package com.joseph.kmmsocialapp.android.presentation.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.components.CircularImage
import com.joseph.kmmsocialapp.android.common.components.FollowButton
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SmallSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.models.UserDetail
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val profileTabs = uiState.tabs

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        val screenHeight = maxHeight
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            if (uiState.isLoading.not()) {
                UserPersonalInfo(uiState.userDetail, uiState.isCurrentUser)
            } else {
                CircularProgressIndicator()
            }
            if (uiState.tabs.isNotEmpty()) {
                Column(modifier = Modifier.height(screenHeight)) {
                    TabRow(
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.background,
                        selectedTabIndex = pagerState.currentPage,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier
                                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                                    .clip(RoundedCornerShape(4.dp)),
                                height = 3.dp,
                                color = MaterialTheme.colors.primary
                            )
                        }
                    ) {
                        profileTabs.forEachIndexed { index, tab ->
                            Tab(
                                text = {
                                    Text(
                                        text = stringResource(id = tab.titleId),
                                        style = MaterialTheme.typography.body2,
                                        color = MaterialTheme.colors.onBackground
                                    )
                                },
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                }
                            )
                        }
                    }

                    HorizontalPager(
                        state = pagerState,
                        pageCount = profileTabs.size,
                        modifier = Modifier
                            .fillMaxHeight()
                            .nestedScroll(remember {
                                object : NestedScrollConnection {
                                    override fun onPreScroll(
                                        available: Offset,
                                        source: NestedScrollSource
                                    ): Offset {
                                        return if (available.y > 0) Offset.Zero else Offset(
                                            x = 0f,
                                            y = -scrollState.dispatchRawDelta(-available.y)
                                        )
                                    }
                                }
                            })
                    ) { page: Int ->
                        profileTabs[page].content()
                    }
                }
            }
        }
    }
}

@Composable
private fun UserPersonalInfo(
    userDetail: UserDetail,
    isCurrentUser: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        val placeholder = painterResource(
            id = if (isSystemInDarkTheme()) R.drawable.dark_image_place_holder
            else R.drawable.light_image_place_holder
        )
        AsyncImage(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(160.dp),
            model = userDetail.profileBackground,
            contentDescription = null,
            placeholder = placeholder,
            contentScale = ContentScale.Crop,
            error = placeholder
        )
        Column(
            modifier = Modifier
                .padding(top = 80.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularImage(
                imageUrl = userDetail.avatar,
                modifier = modifier.size(150.dp),
                placeHolder = painterResource(id = R.drawable.user_placeholder)
            )
            Spacer(modifier = Modifier.height(LargeSpacing))
            Text(
                text = userDetail.fullName(),
                style = MaterialTheme.typography.subtitle1,
            )
            Spacer(modifier = Modifier.height(SmallSpacing))
            if (userDetail.education != null) Text(
                text = userDetail.education,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary
            )
            Spacer(modifier = Modifier.height(MediumSpacing))
            if (userDetail.bio != null) Text(
                text = userDetail.bio,
                style = MaterialTheme.typography.subtitle2,
            )
            Spacer(modifier = Modifier.height(ExtraLargeSpacing))
            FollowingInfo(userDetail, isCurrentUser)
        }
    }
}

@Composable
fun FollowingInfo(
    userDetail: UserDetail,
    isCurrentUser: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = ExtraLargeSpacing)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = userDetail.followersCount.toString(),
                style = MaterialTheme.typography.subtitle2,
            )
            Text(
                text = stringResource(id = R.string.followers),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary
            )
        }
        Column {
            Text(
                text = userDetail.followingCount.toString(),
                style = MaterialTheme.typography.subtitle2,
            )
            Text(
                text = stringResource(id = R.string.following),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary
            )
        }

        if (isCurrentUser) OutlinedButton(
            modifier = Modifier,
            onClick = {},
            border = BorderStroke(1.dp, MaterialTheme.colors.secondary)
        ) {
            Text(
                text = stringResource(id = R.string.edit_profile),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
        } else FollowButton(
            onClick = {},
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    SocialAppTheme {
        ProfileScreen(
            uiState = ProfileUiState()
        )
    }
}
