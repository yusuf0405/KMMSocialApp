package com.joseph.kmmsocialapp.android.common.components

import android.service.autofill.OnClickAction
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.ButtonHeight
import com.joseph.kmmsocialapp.android.common.SocialAppTheme

@Preview
@Composable
fun CustomButtonPreview() {
    SocialAppTheme {
        CustomButton(
            titleResId = R.string.signup_button_hint,
            onClickAction = {

            }
        )
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    @StringRes titleResId: Int,
    onClickAction: () -> Unit
) {
    Button(
        onClick = onClickAction,
        modifier = modifier
            .fillMaxWidth()
            .height(ButtonHeight),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = stringResource(id = titleResId))
    }
}