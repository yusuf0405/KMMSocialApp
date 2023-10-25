package com.joseph.kmmsocialapp.android.common.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.kmmsocialapp.android.common.components.CircularImage
import com.joseph.kmmsocialapp.android.common.fake_data.Message
import com.joseph.kmmsocialapp.android.common.fake_data.randomMassages
import com.joseph.kmmsocialapp.android.common.fake_data.sampleAvatars
import com.joseph.kmmsocialapp.android.common.fake_data.sampleMessages
import com.joseph.kmmsocialapp.android.common.theme.Black24
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.LargeBlue
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SmallSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.common.theme.White36

@Composable
fun MessageItemList(
    messages: List<Message>,
    modifier: Modifier = Modifier,
) {
    val filtratedMessages = messages.groupBy { it.releaseDate }.toList()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(ExtraLargeSpacing),
    ) {
        filtratedMessages.forEach { (releaseDate, messages) ->
            item {
                MessageHeader(releaseDate)
            }
            val yourMassages = messages
                .filter { it.isYouMessage }
                .groupBy { it.releaseTime }
                .toList()
                .map { Pair(true, it) }

            val userMassages = messages
                .filter { !it.isYouMessage }
                .groupBy { it.releaseTime }
                .toList()
                .map { Pair(false, it) }

            val sortedMessages = (yourMassages + userMassages)
                .toList()
                .sortedBy { it.second.first }

            sortedMessages.forEach { (isYouMessage, params) ->
                val (releaseDate, messages) = params
                items(
                    items = messages
                ) { message ->
                    if (message.isYouMessage) {
                        YourMessage(
                            message = message.message,
                        )
                    } else {
                        UserMessage(
                            message = message.message,
                            userAvatar = message.userImage
                        )
                    }
                }
                item {
                    MessageTime(
                        releaseDate = releaseDate,
                        isYouMessage = isYouMessage,
                    )
                }
            }
        }
    }

}

@Composable
private fun MessageTime(
    releaseDate: String,
    isYouMessage: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(top = SmallSpacing)
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.align(
                if (isYouMessage) Alignment.TopEnd
                else Alignment.CenterStart
            ),
            text = releaseDate,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
private fun MessageHeader(
    releaseDate: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(LargeSpacing))
        Text(
            text = releaseDate,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary
        )
        Spacer(modifier = Modifier.height(LargeSpacing))
    }
}

@Composable
private fun UserMessage(
    message: String,
    userAvatar: String,
    modifier: Modifier = Modifier,
) {
    val messageRoundedSize = 12.dp
    Box(
        modifier = modifier
            .padding(top = LargeSpacing)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            CircularImage(
                imageUrl = userAvatar,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(SmallSpacing))

            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 1.dp,
                            topEnd = messageRoundedSize,
                            bottomStart = messageRoundedSize,
                            bottomEnd = messageRoundedSize
                        )
                    )
                    .background(
                        if (isSystemInDarkTheme()) Black24
                        else White36
                    ),

            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = MediumSpacing)
                        .padding(vertical = SmallSpacing),
                    text = message,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
private fun YourMessage(
    message: String,
    modifier: Modifier = Modifier,
) {
    val messageRoundedSize = 12.dp
    Box(
        modifier = modifier
            .padding(top = LargeSpacing)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(
                    RoundedCornerShape(
                        topStart = messageRoundedSize,
                        topEnd = messageRoundedSize,
                        bottomStart = messageRoundedSize,
                        bottomEnd = 1.dp
                    )
                )
                .background(LargeBlue)

        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = MediumSpacing)
                    .padding(vertical = SmallSpacing),
                text = message,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview
@Composable
private fun MessageItemListPreview() {
    SocialAppTheme {
        MessageItemList(
            messages = sampleMessages
        )
    }
}

@Preview
@Composable
private fun YourMessagePreview() {
    SocialAppTheme {
        YourMessage(
            message = randomMassages().random(),
        )
    }
}

@Preview
@Composable
private fun UserMessagePreview() {
    SocialAppTheme {
        UserMessage(
            message = randomMassages().random(),
            userAvatar = sampleAvatars.random()
        )
    }
}