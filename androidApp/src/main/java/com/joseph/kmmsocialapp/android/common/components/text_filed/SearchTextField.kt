package com.joseph.kmmsocialapp.android.common.components.text_filed

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SecondaryDarkGray
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.common.theme.White36

@Composable
fun SearchTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    val backgroundColor = if (isSystemInDarkTheme()) SecondaryDarkGray
    else White36

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
            textColor = MaterialTheme.colors.onBackground,
            placeholderColor = MaterialTheme.colors.onBackground
        ),
        enabled = isEnabled,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .padding(end = MediumSpacing)
                    .fillMaxHeight(),
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    SocialAppTheme {
        SearchTextField(
            value = "",
            placeholder = "Who do you want to chat with?",
            onValueChange = {},
        )
    }
}