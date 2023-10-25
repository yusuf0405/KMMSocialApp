package com.joseph.kmmsocialapp.android.common.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joseph.kmmsocialapp.android.common.components.CircularImage
import com.joseph.kmmsocialapp.android.common.extensions.clickableNoRipple
import com.joseph.kmmsocialapp.android.common.fake_data.Chat
import com.joseph.kmmsocialapp.android.common.fake_data.sampleChats
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme

@Composable
fun ChatItemList(
    chats: List<Chat>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {

        items(
            items = chats,
        ) { chat ->
            ChatItem(
                chat = chat,
                onClick = onClick
            )
        }
    }
}

@Composable
fun ChatItem(
    chat: Chat,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickableNoRipple { onClick(chat.userId) }
    ) {
        Divider()
        Row(
            modifier = Modifier
                .padding(horizontal = ExtraLargeSpacing)
                .padding(vertical = ExtraLargeSpacing)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircularImage(
                imageUrl = chat.userImage,
                modifier = Modifier.size(40.dp),
                onClick = { onClick(chat.userId) }
            )
            Spacer(modifier = Modifier.width(MediumSpacing))
            Column {
                Text(
                    text = chat.userName,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = chat.lastMessage,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = chat.lastMessageSendDate,
                style = MaterialTheme.typography.body2,
                fontSize = 12.sp,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatItemListPreview() {
    SocialAppTheme {
        ChatItemList(
            chats = sampleChats,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatItemPreview() {
    SocialAppTheme {
        ChatItem(
            chat = sampleChats.first(),
            onClick = {}
        )
    }
}