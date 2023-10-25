package com.joseph.kmmsocialapp.android.common.components.text_filed

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.theme.Black
import com.joseph.kmmsocialapp.android.common.theme.SecondaryDarkGray
import com.joseph.kmmsocialapp.android.common.theme.SmallSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.common.theme.White
import com.joseph.kmmsocialapp.android.common.theme.White36

@Composable
fun ChatTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
    isOnlyDark: Boolean = false,
    isEnabled: Boolean = true,
) {
    val backgroundColor = if (isOnlyDark) SecondaryDarkGray
    else if (isSystemInDarkTheme()) SecondaryDarkGray
    else White36

    val textColor = if (isOnlyDark) White
    else if (isSystemInDarkTheme()) White
    else Black

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(32.dp)),
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            focusedIndicatorColor = backgroundColor,
            unfocusedIndicatorColor = backgroundColor,
            textColor = textColor,
            placeholderColor = textColor
        ),
        enabled = isEnabled,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.body2,
                color = textColor.copy(alpha = 0.7f),
            )
        },
        trailingIcon = {
            Image(
                modifier = Modifier
                    .padding(end = SmallSpacing)
                    .fillMaxHeight()
                    .clickable { onSendClick() },
                painter = painterResource(id = R.drawable.chat_field_icon),
                contentDescription = null,
            )
        }
    )
}

@Preview
@Composable
fun ChatTextFieldPreview() {
    SocialAppTheme {
        ChatTextField(
            value = "",
            placeholder = "Type your reply here...",
            onValueChange = {},
            onSendClick = {}
        )
    }
}