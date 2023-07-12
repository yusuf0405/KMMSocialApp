package com.joseph.kmmsocialapp.android.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.joseph.kmmsocialapp.android.common.components.PostListItem
import com.joseph.kmmsocialapp.android.common.fake_data.FollowsUser
import com.joseph.kmmsocialapp.android.common.fake_data.Post
import com.joseph.kmmsocialapp.android.home.onboarding.OnBoardingSelection
import com.joseph.kmmsocialapp.android.home.onboarding.OnBoardingUiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBoardingUiState: OnBoardingUiState,
    postUiState: PostUiState,
    onUserClick: (FollowsUser) -> Unit,
    onFollowButtonClick: (Boolean, FollowsUser) -> Unit,
    onBoardingFinish: () -> Unit,
    onPostClick: (Post) -> Unit,
    onProfileClick: (Int) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    fetchMoreData: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = onBoardingUiState.isLoading && postUiState.isLoading,
        onRefresh = { fetchMoreData() })
    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(state = pullRefreshState)
    ) {
        LazyColumn {
            item(key = "onboarding") {
                OnBoardingSelection(
                    users = onBoardingUiState.users,
                    onUserClick = onUserClick,
                    onFollowButtonClick = onFollowButtonClick
                ) {
                    onBoardingFinish()
                }
            }

            items(count = postUiState.posts.size) { position ->
                val post = postUiState.posts[position]
                PostListItem(
                    post = post,
                    onPostClick = onPostClick,
                    onProfileClick = onProfileClick,
                    onLikeClick = onLikeClick,
                    onCommentClick = onCommentClick
                )
            }
        }

        PullRefreshIndicator(
            refreshing = onBoardingUiState.isLoading && postUiState.isLoading,
            state = pullRefreshState,
            modifier = modifier.align(Alignment.TopCenter)
        )
    }
}












