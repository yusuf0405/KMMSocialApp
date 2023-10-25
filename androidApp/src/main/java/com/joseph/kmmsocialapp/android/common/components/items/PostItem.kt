package com.joseph.kmmsocialapp.android.common.components.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import com.joseph.kmmsocialapp.android.common.components.CircularImage
import com.joseph.kmmsocialapp.android.common.components.HorizontalPagerIndicator
import com.joseph.kmmsocialapp.android.common.fake_data.Post
import com.joseph.kmmsocialapp.android.common.fake_data.samplePhotoPosts
import com.joseph.kmmsocialapp.android.common.fake_data.sampleTextPosts
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SmallSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.presentation.screens.home.PostActions

fun LazyListScope.postList(
    posts: List<Post>,
    actions: PostActions,
) {
    items(
        items = posts,
        key = { item -> item.storiesId }
    ) { post ->
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
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoPostItem(
    modifier: Modifier = Modifier,
    post: Post.PhotoPost,
    onPostClick: (String) -> Unit,
    onProfileClick: (Int) -> Unit = {},
    onLikeClick: () -> Unit = {},
    onCommentClick: () -> Unit = {},
    isDetailScreen: Boolean = false
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { onPostClick(post.id) }
            .padding(bottom = ExtraLargeSpacing),
    ) {
        Divider()
        Spacer(modifier = Modifier.height(SmallSpacing))
        PostItemHeader(
            name = post.authorName,
            profileUrl = post.authorImage,
            date = post.createdAt,
            onProfileClick = { onProfileClick(post.authorId) }
        )
        HorizontalPager(
            pageCount = post.imageUrls.size,
            state = pagerState
        ) { index ->
            AsyncImage(
                model = post.imageUrls[index],
                contentDescription = null,
                modifier = modifier
                    .padding(vertical = MediumSpacing)
                    .padding(horizontal = LargeSpacing)
                    .fillMaxWidth()
                    .aspectRatio(ratio = 1.0f)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                placeholder = if (MaterialTheme.colors.isLight) {
                    painterResource(id = R.drawable.light_image_place_holder)
                } else {
                    painterResource(id = R.drawable.dark_image_place_holder)
                },
            )
        }
        if (post.imageUrls.size > 1) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                HorizontalPagerIndicator(
                    modifier = Modifier,
                    pagerState = pagerState,
                    pageCount = post.imageUrls.size,
                )
            }
        }
        PostLikesRow(
            modifier = Modifier.padding(horizontal = LargeSpacing),
            likesCount = post.likesCount,
            commentsCount = post.commentCount,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick
        )
        Text(
            text = post.text,
            style = MaterialTheme.typography.body2,
            modifier = modifier.padding(horizontal = LargeSpacing),
            maxLines = if (isDetailScreen) 20 else 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun TextPostItem(
    modifier: Modifier = Modifier,
    post: Post.TextPost,
    onPostClick: (String) -> Unit,
    onProfileClick: (Int) -> Unit = {},
    onLikeClick: () -> Unit = {},
    onCommentClick: () -> Unit = {},
    isDetailScreen: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { onPostClick(post.id) }
            .padding(bottom = MediumSpacing),
    ) {
        Divider()
        Spacer(modifier = Modifier.height(SmallSpacing))
        PostItemHeader(
            name = post.authorName,
            profileUrl = post.authorImage,
            date = post.createdAt,
            onProfileClick = { onProfileClick(post.authorId) }
        )
        Text(
            modifier = modifier
                .padding(top = MediumSpacing)
                .padding(horizontal = LargeSpacing + MediumSpacing),
            text = post.text,
            style = MaterialTheme.typography.body1,
            maxLines = if (isDetailScreen) Int.MAX_VALUE else 9,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground
        )
        PostLikesRow(
            modifier = Modifier.padding(horizontal = LargeSpacing),
            likesCount = post.likesCount,
            commentsCount = post.commentCount,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick
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
            placeHolder = painterResource(id = R.drawable.user_placeholder)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier.clickable { onProfileClick() },
            color = MaterialTheme.colors.onBackground
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
                color = MaterialTheme.colors.secondary
            ),
            modifier = modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.round_more_horiz_24),
            contentDescription = null,
            tint = MaterialTheme.colors.secondary
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
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onLikeClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.like_icon_outlined),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary,
            )
        }
        Text(
            text = "$likesCount",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.width(MediumSpacing))

        IconButton(onClick = onCommentClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.chat_icon_outlined),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary,
            )
        }
        Text(
            text = "$commentsCount",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Preview
@Composable
fun PostListItemPreview() {
    SocialAppTheme {
        PhotoPostItem(
            post = samplePhotoPosts.first(),
            onPostClick = {},
        )
    }
}

@Preview
@Composable
fun TextPostItemPreview() {
    SocialAppTheme {
        TextPostItem(
            post = sampleTextPosts.first(),
            onPostClick = {},
        )
    }
}


@Preview
@Composable
fun PostItemHeaderPreview() {
    SocialAppTheme {
        PostItemHeader(
            name = samplePhotoPosts.first().authorName,
            profileUrl = samplePhotoPosts.first().imageUrls.first(),
            date = samplePhotoPosts.first().createdAt,
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












