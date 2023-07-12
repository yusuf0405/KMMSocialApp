package com.joseph.kmmsocialapp.android.post

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination

@Composable
fun PostDetail(
    navigator: DestinationsNavigator,
    postId: String
) {
    val viewModel: PostDetailScreenViewModel = koinViewModel()

    PostDetailScreen(
        postUiState = viewModel.postDetailUiState,
        commentsUiState = viewModel.commentsUiState,
        onCommentMoreIconClick = viewModel::onCommentMoreIconClick,
        onProfileClick = viewModel::onProfileClick,
        onAddCommentClick = viewModel::onAddCommentClick,
        fetchData = { viewModel.fetchData(postId) }
    )
}