package com.joseph.kmmsocialapp.android.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.fake_data.Post
import com.joseph.kmmsocialapp.android.common.fake_data.samplePosts
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme

@Preview
@Composable
fun PostItemHeaderPreview() {
    SocialAppTheme {
        PostItemHeader(
            name = samplePosts.first().authorName,
            profileUrl = samplePosts.first().imageUrl,
            date = samplePosts.first().createdAt,
            onProfileClick = {}
        )
    }
}

@Composable
@Preview
fun PostLikesRowPreview() {
    SocialAppTheme {
        PostLikesRow(
            likesCount = 11,
            commentsCount = 9,
            onLikeClick = {},
            onCommentClick = {}
        )
    }
}

@Preview
@Composable
fun PostListItemPreview() {
    SocialAppTheme {
        PostListItem(
            post = samplePosts.first(),
            onProfileClick = {},
            onLikeClick = {},
            onCommentClick = {},
            onPostClick = {},
        )
    }
}

@Composable
fun PostListItem(
    modifier: Modifier = Modifier,
    post: Post,
    onPostClick: (Post) -> Unit,
    onProfileClick: (Int) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    isDetailScreen: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.surface)
            .clickable { onPostClick(post) }
            .padding(bottom = ExtraLargeSpacing),

        ) {
        PostItemHeader(
            name = post.authorName,
            profileUrl = post.authorImage,
            date = post.createdAt,
            onProfileClick = { onProfileClick(post.authorId) }
        )
        AsyncImage(
            model = post.imageUrl,
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 1.0f),
            contentScale = ContentScale.Crop,
            placeholder = if (MaterialTheme.colors.isLight) {
                painterResource(id = R.drawable.light_image_place_holder)
            } else {
                painterResource(id = R.drawable.dark_image_place_holder)
            },
        )

        PostLikesRow(
            likesCount = post.likesCount,
            commentsCount = post.commentCount,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick
        )
        Text(
            text = post.text,
            style = MaterialTheme.typography.body2,
            modifier = modifier.padding(
                horizontal = LargeSpacing
            ),
            maxLines = if (isDetailScreen) 20 else 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun PostItemHeader(
    modifier: Modifier = Modifier,
    name: String,
    profileUrl: String,
    date: String,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = LargeSpacing,
                vertical = MediumSpacing
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        CircularImage(
            modifier = modifier
                .size(30.dp)
                .clickable { onProfileClick() },
            imageUrl = profileUrl,
        )
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier.clickable { onProfileClick() }
        )
        Box(
            modifier = modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(
                    color = if (MaterialTheme.colors.isLight) Color.LightGray
                    else Color.DarkGray
                )
        )
        Text(
            text = date,
            style = MaterialTheme.typography.caption.copy(
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                color = if (MaterialTheme.colors.isLight) Color.LightGray
                else Color.DarkGray
            ),
            modifier = modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.round_more_horiz_24),
            contentDescription = null,
            tint = if (MaterialTheme.colors.isLight) Color.LightGray
            else Color.DarkGray
        )
    }
}


@Composable
fun PostLikesRow(
    modifier: Modifier = Modifier,
    likesCount: Int,
    commentsCount: Int,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 0.dp,
                horizontal = MediumSpacing
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onLikeClick) {
            Icon(
                painter = painterResource(id = R.drawable.like_icon_outlined),
                contentDescription = null,
                tint = if (MaterialTheme.colors.isLight) Color.LightGray
                else Color.DarkGray,
            )
        }

        Text(
            text = "$likesCount",
            style = MaterialTheme.typography.subtitle2.copy(
                fontSize = 18.sp
            )
        )
        Spacer(modifier = modifier.width(MediumSpacing))

        IconButton(onClick = onCommentClick) {
            Icon(
                painter = painterResource(id = R.drawable.chat_icon_outlined),
                contentDescription = null,
                tint = if (MaterialTheme.colors.isLight) Color.LightGray
                else Color.DarkGray,
            )
        }

        Text(
            text = "$commentsCount",
            style = MaterialTheme.typography.subtitle2.copy(
                fontSize = 18.sp
            )
        )
    }
}














