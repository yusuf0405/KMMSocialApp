package com.joseph.kmmsocialapp.android.home

import androidx.compose.runtime.Composable
import com.joseph.kmmsocialapp.android.destinations.PostDetailDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


@Destination(start = true)
@Composable
fun Home(
    navigator: DestinationsNavigator,
) {
    val viewModel: HomeViewModel = koinViewModel()
    HomeScreen(
        postUiState = viewModel.postUiState,
        onBoardingUiState = viewModel.onBoardingState,
        onUserClick = viewModel::onUserClick,
        onFollowButtonClick = viewModel::onFollowButtonClick,
        onBoardingFinish = viewModel::onBoardingFinish,
        onProfileClick = viewModel::onProfileClick,
        onLikeClick = viewModel::onLikeClick,
        onCommentClick = viewModel::onCommentClick,
        onPostClick = { post ->
            navigator.navigate(PostDetailDestination(post.id))
        },
        fetchMoreData = viewModel::fetchMoreData
    )
}