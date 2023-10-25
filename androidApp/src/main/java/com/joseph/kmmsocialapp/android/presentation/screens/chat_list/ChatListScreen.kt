package com.joseph.kmmsocialapp.android.presentation.screens.chat_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.joseph.kmmsocialapp.android.common.components.items.ChatItem
import com.joseph.kmmsocialapp.android.common.components.items.PinnedUserList
import com.joseph.kmmsocialapp.android.common.components.text_filed.SearchTextField
import com.joseph.kmmsocialapp.android.common.fake_data.FollowsUser
import com.joseph.kmmsocialapp.android.common.fake_data.sampleChats
import com.joseph.kmmsocialapp.android.common.fake_data.sampleUsers
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme

@Composable
fun ChatListScreen(
    navigateToChat: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    var searchQuery by remember { mutableStateOf("") }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        item {
            Spacer(modifier = Modifier.height(ExtraLargeSpacing))
            SearchTextField(
                modifier = Modifier
                    .padding(horizontal = ExtraLargeSpacing),
                value = searchQuery,
                placeholder = "Who do you want to chat with?",
                onValueChange = { searchQuery = it }
            )
            Spacer(modifier = Modifier.height(ExtraLargeSpacing))
            PinnedUsers()
        }
        items(
            items = sampleChats,
        ) { chat ->
            ChatItem(
                chat = chat,
                onClick = navigateToChat
            )
        }
    }

}

@Composable
fun PinnedUsers(
    pinnedUsers: List<FollowsUser> = sampleUsers,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        Divider()
        Spacer(modifier = Modifier.height(LargeSpacing))
        Text(
            modifier = Modifier.padding(start = ExtraLargeSpacing),
            text = "PINNED",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground.copy(0.8f)
        )
        PinnedUserList(pinnedUsers = pinnedUsers)
    }
}


@Preview
@Composable
fun ChatListScreenPreview() {
    SocialAppTheme {
        ChatListScreen(
            navigateToChat = {}
        )
    }
}