@file:OptIn(ExperimentalAnimationGraphicsApi::class)

package com.joseph.kmmsocialapp.android.presentation.screens.create

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.components.CircularImage
import com.joseph.kmmsocialapp.android.common.components.HorizontalPagerIndicator
import com.joseph.kmmsocialapp.android.common.provider.file.ComposeFileProvider
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.ExtraMediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SmallSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme

@Composable
fun CreateScreen(
    actions: CreateScreenActions,
    uiState: CreateUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        CreateScreenToolbar(actions)
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = ExtraLargeSpacing)
                .fillMaxWidth()
        ) {
            CircularImage(
                imageUrl = uiState.user.avatar,
                modifier = Modifier
                    .padding(top = ExtraMediumSpacing)
                    .size(32.dp)
            )
            TextField(
                value = uiState.message ?: String(),
                onValueChange = actions::setMessage,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.whats_on_your_mind),
                        style = MaterialTheme.typography.body2,
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent
                )
            )
        }
        PhotoContent(
            actions = actions,
            uiState = uiState
        )
    }
}

@Composable
fun PhotoContent(
    actions: CreateScreenActions,
    uiState: CreateUiState,
) {
    val context = LocalContext.current
    var isShowCloseIcon by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val drawableId = if (isShowCloseIcon) R.drawable.close_icon
    else R.drawable.plus_icon

    val photoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> uri?.let { actions.setImageUri(uri) } }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
    ) { success ->
        if (success) actions.setImageUri(imageUri)
    }

    Row(
        modifier = Modifier.padding(horizontal = ExtraLargeSpacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.secondary.copy(alpha = 0.5f),
                    shape = CircleShape
                )
                .size(32.dp)
                .clickable { isShowCloseIcon = isShowCloseIcon.not() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = drawableId),
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground
            )
        }
        Spacer(modifier = Modifier.width(ExtraMediumSpacing))
        AnimatedVisibility(visible = isShowCloseIcon) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(32.dp))
                    .background(MaterialTheme.colors.surface)
                    .padding(vertical = 6.dp),
            ) {
                Spacer(modifier = Modifier.width(LargeSpacing))
                Icon(
                    modifier = Modifier.clickable { photoLauncher.launch("image/*") },
                    painter = painterResource(id = R.drawable.gallery_icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(LargeSpacing))
                Icon(
                    painter = painterResource(id = R.drawable.gif_icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(LargeSpacing))
                Icon(
                    modifier = Modifier.clickable {
                        val uri = ComposeFileProvider.getImageUri(context)
                        imageUri = uri
                        cameraLauncher.launch(uri)
                    },
                    painter = painterResource(id = R.drawable.camera_icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(LargeSpacing))
                Icon(
                    painter = painterResource(id = R.drawable.attachment_icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(LargeSpacing))
            }
        }
    }
    Spacer(modifier = Modifier.height(ExtraLargeSpacing + ExtraMediumSpacing))
    PostPhotoPager(uiState.uriList)
}

@Composable
fun CreateScreenToolbar(
    actions: CreateScreenActions,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .padding(horizontal = ExtraLargeSpacing)
            .padding(top = MediumSpacing)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.clickable { actions.discard() },
            text = stringResource(id = R.string.discard),
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.primary
        )
        Text(
            text = stringResource(id = R.string.create_destination_title),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colors.primary)
                .clickable { actions.addPost(context.contentResolver) }
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = MediumSpacing)
                    .padding(vertical = SmallSpacing),
                text = stringResource(id = R.string.publish),
                style = MaterialTheme.typography.subtitle2,
                color = Color.White
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostPhotoPager(
    uriList: List<Uri>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = modifier
            .padding(top = LargeSpacing)
            .padding(bottom = ExtraLargeSpacing + ExtraLargeSpacing)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            modifier = modifier,
            pageCount = uriList.size,
            state = pagerState
        ) { index ->
            PostImagePage(
                pagerState = pagerState,
                index = index,
                uri = uriList[index]
            )
        }

        if (uriList.size > 1) {
            Box(
                modifier = Modifier
                    .padding(top = LargeSpacing)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                HorizontalPagerIndicator(
                    modifier = Modifier,
                    pagerState = pagerState,
                    pageCount = uriList.size,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostImagePage(
    uri: Uri,
    index: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    val matrix = remember { ColorMatrix() }
    val pagerOffset =
        (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction

    val imageSize by animateFloatAsState(
        targetValue = if (pagerOffset != 0.0f) 0.75f else 1f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    LaunchedEffect(key1 = imageSize) {
        val saturation = if (pagerOffset != 0.0f) 0f
        else 1f
        matrix.setToSaturation(saturation)
    }
    AsyncImage(
        modifier = modifier
            .padding(horizontal = ExtraLargeSpacing + MediumSpacing)
            .clip(RoundedCornerShape(LargeSpacing))
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = imageSize
                scaleY = imageSize
                shape = RoundedCornerShape(LargeSpacing)
                clip = true
            },
        model = uri,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(matrix),
        placeholder = if (isSystemInDarkTheme()) {
            painterResource(id = R.drawable.dark_image_place_holder)
        } else painterResource(id = R.drawable.light_image_place_holder)
    )
}

@Preview
@Composable
fun CreateScreenPreview() {
    SocialAppTheme {
        CreateScreen(
            actions = CreateScreenActions.Preview,
            uiState = CreateUiState()
        )
    }
}