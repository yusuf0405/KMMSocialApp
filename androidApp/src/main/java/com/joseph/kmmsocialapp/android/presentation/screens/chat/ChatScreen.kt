package com.joseph.kmmsocialapp.android.presentation.screens.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joseph.kmmsocialapp.android.common.components.items.MessageItemList
import com.joseph.kmmsocialapp.android.common.components.text_filed.ChatTextField
import com.joseph.kmmsocialapp.android.common.fake_data.Chat
import com.joseph.kmmsocialapp.android.common.fake_data.sampleMessages

@Composable
fun ChatScreen(
    chat: Chat,
    modifier: Modifier = Modifier
) {
    Column {
        MessageItemList(
            messages = sampleMessages,
            modifier = Modifier.weight(1f)
        )
        ChatTextField(
            value = "",
            placeholder = "Type your reply here...",
            onValueChange = {},
            onSendClick = {},
            modifier = Modifier
        )
    }
}