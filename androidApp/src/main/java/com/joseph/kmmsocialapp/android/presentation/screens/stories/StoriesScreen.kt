package com.joseph.kmmsocialapp.android.presentation.screens.stories

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.joseph.kmmsocialapp.android.common.components.text_filed.ChatTextField
import com.joseph.kmmsocialapp.android.common.components.CircularStoriesImage
import com.joseph.kmmsocialapp.android.common.fake_data.Stories
import com.joseph.kmmsocialapp.android.common.fake_data.fakeStories
import com.joseph.kmmsocialapp.android.common.theme.Black24
import com.joseph.kmmsocialapp.android.common.theme.ExtraMediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SmallSpacing
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoriesScreen(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val userStories = fakeStories.groupBy { it.userId }.toList()
    HorizontalPager(
        pageCount = userStories.size,
        state = pagerState,
        beyondBoundsPageCount = 0,
        key = { index -> userStories[index].first }
    ) { index ->
        val stories = userStories[index].second
        StoryItem(
            stories = stories,
            onNextPage = {
                scope.launch {
                    val nextPage = min(userStories.size - 1, pagerState.currentPage + 1)
                    pagerState.animateScrollToPage(nextPage)
                }
            },
            onPreviousPage = {
                scope.launch {
                    val previousPage = max(0, pagerState.currentPage - 1)
                    pagerState.animateScrollToPage(previousPage)
                }
            }
        )
    }
}

@Composable
fun StoryItem(
    stories: List<Stories>,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentStep by remember { mutableStateOf(0) }
    var isPaused by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val imageModifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { offset ->
                        val step = if (offset.x < constraints.maxWidth / 2) {
                            currentStep - 1
                        } else {
                            currentStep + 1
                        }
                        when (step) {
                            stories.size -> {
                                currentStep = 0
                                onNextPage()
                            }

                            -1 -> onPreviousPage()
                            else -> currentStep = step
                        }

                    },
                    onPress = {
                        try {
                            isPaused = true
                            awaitRelease()
                        } finally {
                            isPaused = false
                        }
                    }
                )
            }

        AsyncImage(
            model = stories[currentStep].imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = imageModifier,
        )

        Column(
            modifier = Modifier.padding(horizontal = LargeSpacing)
        ) {
            ProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                stepCount = stories.size,
                stepDuration = 5_000,
                unSelectedColor = Color.LightGray,
                selectedColor = Color.White,
                currentStep = currentStep,
                onStepChanged = { currentStep = it },
                isPaused = isPaused
            ) {
                currentStep = 0
                onNextPage()
            }
            UserInfoBlock(stories[currentStep])
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .align(Alignment.BottomCenter)
                .background(Black24.copy(alpha = 0.6f))
        ) {
            ChatTextField(
                modifier = Modifier
                    .padding(horizontal = LargeSpacing)
                    .padding(top = LargeSpacing)
                    .padding(bottom = MediumSpacing),
                value = value,
                placeholder = "Type your reply here...",
                onValueChange = { value = it },
                onSendClick = {},
                isOnlyDark = true,
                isEnabled = true,
            )
        }

    }
}

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    stepCount: Int,
    stepDuration: Int,
    unSelectedColor: Color,
    selectedColor: Color,
    currentStep: Int,
    onStepChanged: (Int) -> Unit,
    isPaused: Boolean = false,
    onComplete: () -> Unit,
) {
    val currentStepState = remember(currentStep) { mutableStateOf(currentStep) }
    val progress = remember(currentStep) { Animatable(0f) }

    Row(
        modifier = modifier.padding(top = MediumSpacing),
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        for (i in 0 until stepCount) {
            val stepProgress = when {
                i == currentStepState.value -> progress.value
                i > currentStepState.value -> 0f
                else -> 1f
            }
            LinearProgressIndicator(
                color = selectedColor,
                backgroundColor = unSelectedColor,
                progress = stepProgress,
                modifier = Modifier
                    .weight(1f)
                    .height(3.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }

    LaunchedEffect(isPaused, currentStep) {
        if (isPaused) {
            progress.stop()
        } else {
            for (i in currentStep until stepCount) {
                progress.animateTo(
                    1f,
                    animationSpec = tween(
                        durationMillis = ((1f - progress.value) * stepDuration).toInt(),
                        easing = LinearEasing
                    )
                )

                if (currentStepState.value + 1 <= stepCount - 1) {
                    progress.snapTo(0f)
                    currentStepState.value += 1
                    onStepChanged(currentStepState.value)
                } else {
                    onComplete()
                }
            }
        }
    }
}

@Composable
fun UserInfoBlock(
    stories: Stories,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(ExtraMediumSpacing))
    Row(
        modifier = modifier.padding(
            horizontal = SmallSpacing
        )
    ) {
        CircularStoriesImage(
            imageUrl = stories.userImage,
            isViewed = stories.isViewed,
            imageModifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(ExtraMediumSpacing))
        Column {
            Text(
                text = stories.userName,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = stories.releasedDate,
                style = MaterialTheme.typography.body2,
                fontSize = 11.sp
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewInstagramStoriesApp() {
    StoryItem(
        stories = fakeStories,
        onPreviousPage = {},
        onNextPage = {},
    )
}
