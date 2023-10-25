package com.joseph.kmmsocialapp.android.common.components

import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joseph.kmmsocialapp.android.R

@Composable
fun FollowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes textId: Int = R.string.follow_button_text,
    isOutline: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = if (isOutline) ButtonDefaults.outlinedButtonColors()
        else ButtonDefaults.buttonColors(),
        border = if (isOutline) ButtonDefaults.outlinedBorder
        else null,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        )
    ) {
        Text(
            text = stringResource(id = textId),
            style = MaterialTheme.typography.button.copy(fontSize = 12.sp),
            color = Color.White
        )
    }
}
