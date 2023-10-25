package com.joseph.kmmsocialapp.android.common.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.theme.Blue
import com.joseph.kmmsocialapp.android.common.theme.LargeBlue
import com.joseph.kmmsocialapp.android.common.theme.LightGray
import com.joseph.kmmsocialapp.android.common.theme.MediumBlue
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme

@Composable
fun CircularImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    placeHolder: Painter? = null,
    onClick: () -> Unit = {},
) {
    val placeholder = placeHolder ?: if (MaterialTheme.colors.isLight) {
        painterResource(id = R.drawable.light_image_place_holder)
    } else {
        painterResource(id = R.drawable.dark_image_place_holder)
    }
    
    AsyncImage(
        model = imageUrl, contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() },
        placeholder = placeholder,
        contentScale = ContentScale.Crop,
        error = placeholder,
    )
}

@Composable
fun CircularStoriesImage(
    imageUrl: String,
    isViewed: Boolean,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val borderModifier = if (isViewed) modifier.border(
        width = 2.dp,
        color = LightGray,
        shape = CircleShape
    ) else modifier.border(
        width = 2.dp,
        brush = fetchStoriesGradient(),
        shape = CircleShape
    )
    Box(
        modifier = borderModifier
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = imageModifier
                .padding(4.dp)
                .clip(CircleShape)
                .clickable { onClick() },
            placeholder = if (MaterialTheme.colors.isLight) {
                painterResource(id = R.drawable.light_image_place_holder)
            } else {
                painterResource(id = R.drawable.dark_image_place_holder)
            },
            contentScale = ContentScale.Crop,
            error = if (MaterialTheme.colors.isLight) {
                painterResource(id = R.drawable.light_image_place_holder)
            } else {
                painterResource(id = R.drawable.dark_image_place_holder)
            },
        )
    }
}

@Composable
private fun fetchStoriesGradient() = Brush.verticalGradient(
    colors = listOf(
        Blue,
        MediumBlue,
        LargeBlue
    ),
)

@Preview
@Composable
fun CircularImagePreview() {
    SocialAppTheme {
        CircularImage(
            imageUrl = "",
            onClick = {},
            modifier = Modifier.size(125.dp)
        )
    }
}

@Preview
@Composable
fun CircularStoriesImagePreview() {
    SocialAppTheme {
        CircularStoriesImage(
            imageUrl = "",
            onClick = {},
            isViewed = false,
            modifier = Modifier.size(125.dp)
        )
    }
}