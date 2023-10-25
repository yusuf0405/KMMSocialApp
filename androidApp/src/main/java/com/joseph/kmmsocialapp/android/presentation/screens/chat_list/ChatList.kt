package com.joseph.kmmsocialapp.android.presentation.screens.chat_list

import androidx.compose.runtime.Composable
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.ChatDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ChatList(
    navigator: DestinationsNavigator,
) {
    ChatListScreen(
        navigateToChat = { chatId ->
            navigator.navigate(ChatDestination(chatId))
        }
    )
}