package com.joseph.kmmsocialapp.android.presentation.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.joseph.kmmsocialapp.android.common.animation.AnimateFade
import com.joseph.kmmsocialapp.android.common.components.items.StoriesList
import com.joseph.kmmsocialapp.android.common.components.items.postList
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
    postsUiState: PostsUiState,
    fetchMoreData: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = postsUiState.isLoading,
        onRefresh = { fetchMoreData() })

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .pullRefresh(state = pullRefreshState)
    ) {
        if (!postsUiState.isLoading) {
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
                postList(
                    posts = postsUiState.posts,
                    actions = actions,
                )
            }
        }

        PullRefreshIndicator(
            refreshing = postsUiState.isLoading,
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
            postsUiState = PostsUiState(isLoading = false),
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
            postsUiState = PostsUiState(isLoading = false),
            actions = PostActions.Preview,
            fetchMoreData = {}
        )
    }
}






