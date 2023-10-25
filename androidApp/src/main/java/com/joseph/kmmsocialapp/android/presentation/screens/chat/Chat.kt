package com.joseph.kmmsocialapp.android.presentation.screens.chat

import androidx.compose.runtime.Composable
import com.joseph.kmmsocialapp.android.common.fake_data.sampleChats
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun Chat(
    chatId: String,
) {
    ChatScreen(
        chat = sampleChats.first()
    )
}