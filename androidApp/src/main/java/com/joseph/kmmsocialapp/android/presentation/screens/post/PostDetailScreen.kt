package com.joseph.kmmsocialapp.android.presentation.screens.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.components.items.CommentsListItem
import com.joseph.kmmsocialapp.android.common.components.items.PhotoPostItem
import com.joseph.kmmsocialapp.android.common.components.items.TextPostItem
import com.joseph.kmmsocialapp.android.common.fake_data.Comment
import com.joseph.kmmsocialapp.android.common.fake_data.Post
import com.joseph.kmmsocialapp.android.common.fake_data.sampleComments
import com.joseph.kmmsocialapp.android.common.fake_data.samplePhotoPosts
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme

@Composable
@Preview
fun PostDetailScreenPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.surface) {
            PostDetailScreen(
                postUiState = PostDetailUiState(
                    isLoading = false,
                    post = samplePhotoPosts.random()
                ),
                commentsUiState = CommentsUiState(
                    isLoading = false,
                    comments = sampleComments
                ),
                onProfileClick = {},
                onAddCommentClick = {},
                onCommentMoreIconClick = {},
                fetchData = {}
            )
        }
    }
}

@Composable
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    postUiState: PostDetailUiState,
    commentsUiState: CommentsUiState,
    onCommentMoreIconClick: (Comment) -> Unit,
    onProfileClick: (Int) -> Unit,
    onAddCommentClick: () -> Unit,
    fetchData: () -> Unit
) {
    if (postUiState.isLoading && commentsUiState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (postUiState.post != null) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colors.background
                ),
        ) {
            item(
                key = "post_item"
            ) {
                when (val post = postUiState.post) {
                    is Post.PhotoPost -> {
                        PhotoPostItem(
                            post = post,
                            onPostClick = {},
                            onProfileClick = onProfileClick,
                            isDetailScreen = true

                        )
                    }

                    is Post.TextPost -> {
                        TextPostItem(
                            post = post,
                            onPostClick = {},
                            onProfileClick = onProfileClick,
                            isDetailScreen = true
                        )
                    }

                    else -> Unit
                }
            }
            item(key = "comments_item") {
                CommentsSelectionHeader(onAddCommentClick = onAddCommentClick)
            }

            items(
                items = sampleComments,
                key = { comment -> comment.id },
            ) { comment ->
                Divider()
                CommentsListItem(
                    comment = comment,
                    onProfileClick = onProfileClick,
                    onMoreIconClick = {
                        onCommentMoreIconClick(comment)
                    }
                )
            }
        }
    } else {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(LargeSpacing)
            ) {
                Text(
                    text = stringResource(id = R.string.loading_post_error),
                    style = MaterialTheme.typography.caption
                )

                OutlinedButton(
                    onClick = fetchData
                ) {
                    Text(
                        text = stringResource(id = R.string.retry_button_label)
                    )
                }
            }
        }
    }

    LaunchedEffect(
        key1 = Unit,
        block = { fetchData() }
    )
}

@Composable
fun CommentsSelectionHeader(
    modifier: Modifier = Modifier,
    onAddCommentClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(LargeSpacing),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.commnts_header_label),
            style = MaterialTheme.typography.subtitle1
        )

        OutlinedButton(
            onClick = {}
        ) {
            Text(text = stringResource(id = R.string.new_comment_button_label))
        }
    }
}