package com.joseph.kmmsocialapp.android.presentation.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.kmmsocialapp.android.common.animation.AnimateFade
import com.joseph.kmmsocialapp.android.common.components.items.PhotoPostItem
import com.joseph.kmmsocialapp.android.common.components.items.StoriesList
import com.joseph.kmmsocialapp.android.common.components.items.TextPostItem
import com.joseph.kmmsocialapp.android.common.fake_data.Post
import com.joseph.kmmsocialapp.android.common.fake_data.fakeStories
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.presentation.screens.home.onboarding.OnBoardingSelection
import com.joseph.kmmsocialapp.android.presentation.screens.home.onboarding.OnBoardingUiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    actions: PostActions,
    onBoardingUiState: OnBoardingUiState,
    uiState: PostsUiState,
    fetchMoreData: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.refreshing,
        onRefresh = { fetchMoreData(true) })

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .pullRefresh(state = pullRefreshState)
    ) {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(MediumSpacing + ExtraLargeSpacing))
                StoriesList(
                    storiesList = fakeStories,
                    onStoriesClick = actions::onStoriesClick
                )
                Spacer(modifier = Modifier.height(ExtraLargeSpacing))
            }

            item {
                AnimateFade(isVisible = onBoardingUiState.shouldShowOnBoarding) {
                    OnBoardingSelection(
                        users = onBoardingUiState.users,
                        onUserClick = actions::onUserClick,
                        onFollowButtonClick = actions::onFollowButtonClick,
                        onBoardingFinish = actions::onBoardingFinish
                    )
                }
            }
            itemsIndexed(
                items = uiState.posts,
                key = { _, item -> item.storiesId }
            ) { index, post ->
                when (post) {
                    is Post.PhotoPost -> {
                        PhotoPostItem(
                            post = post,
                            onPostClick = actions::onPostClick,
                            onProfileClick = actions::onProfileClick,
                            onLikeClick = actions::onLikeClick,
                            onCommentClick = actions::onCommentClick
                        )
                    }

                    is Post.TextPost -> {
                        TextPostItem(
                            post = post,
                            onPostClick = actions::onPostClick,
                            onProfileClick = actions::onProfileClick,
                            onLikeClick = actions::onStoriesClick,
                            onCommentClick = actions::onBoardingFinish
                        )
                    }
                }
                if (index >= uiState.posts.size - 1
                    && !uiState.isLoading && !uiState.loadFinished
                ) {
                    LaunchedEffect(key1 = Unit, block = { fetchMoreData(false) })
                }
            }
            if (uiState.isLoading && uiState.posts.isNotEmpty()) {
                item {
                    Row(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = uiState.refreshing,
            state = pullRefreshState,
            modifier = modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    SocialAppTheme {
        HomeScreen(
            onBoardingUiState = OnBoardingUiState(),
            uiState = PostsUiState(isLoading = false),
            actions = PostActions.Preview,
            fetchMoreData = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenNightPreview() {
    SocialAppTheme {
        HomeScreen(
            onBoardingUiState = OnBoardingUiState(),
            uiState = PostsUiState(isLoading = false),
            actions = PostActions.Preview,
            fetchMoreData = {}
        )
    }
}






