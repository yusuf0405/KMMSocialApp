package com.joseph.kmmsocialapp.android.common.components.items

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.components.CircularStoriesImage
import com.joseph.kmmsocialapp.android.common.fake_data.Stories
import com.joseph.kmmsocialapp.android.common.fake_data.fakeStories
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.ExtraMediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SmallSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme

@Composable
fun StoriesList(
    storiesList: List<Stories>,
    onStoriesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = ExtraLargeSpacing),
        horizontalArrangement = Arrangement.spacedBy(ExtraMediumSpacing)
    ) {
        items(
            items = storiesList.groupBy { it.userId }.toList(),
            key = { stories -> stories.hashCode() }
        ) { stories ->
            StoriesItem(
                stories = stories.second.first(),
                isViewed = stories.second.all { it.isViewed },
                onStoriesClick = onStoriesClick
            )
        }
    }
}

@Composable
fun StoriesItem(
    stories: Stories,
    isViewed: Boolean,
    onStoriesClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(100.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onStoriesClick() }
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = stories.imageUrl,
            contentDescription = null,
            placeholder = painterResource(
                id = if (isSystemInDarkTheme()) R.drawable.dark_image_place_holder
                else R.drawable.light_image_place_holder
            ),
            contentScale = ContentScale.Crop
        )
        CircularStoriesImage(
            modifier = Modifier
                .padding(bottom = SmallSpacing)
                .align(Alignment.BottomCenter),
            imageModifier = Modifier.size(40.dp),
            imageUrl = stories.userImage,
            isViewed =isViewed,
        )
    }
}

@Preview
@Composable
fun StoriesListPreview() {
    SocialAppTheme {
        StoriesList(
            storiesList = fakeStories,
            onStoriesClick = {}
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun StoriesListNightPreview() {
    SocialAppTheme {
        StoriesList(
            storiesList = fakeStories,
            onStoriesClick = {}
        )
    }
}

@Preview
@Composable
fun StoriesItemPreview() {
    SocialAppTheme {
        StoriesItem(
            stories = fakeStories.random(),
            isViewed = false,
            onStoriesClick = {}
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun StoriesItemNightPreview() {
    SocialAppTheme {
        StoriesItem(
            stories = fakeStories.random(),
            isViewed = false,
            onStoriesClick = {}
        )
    }
}