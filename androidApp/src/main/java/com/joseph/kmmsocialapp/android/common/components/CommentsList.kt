package com.joseph.kmmsocialapp.android.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.fake_data.Comment
import com.joseph.kmmsocialapp.android.common.fake_data.sampleComments
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme

@Preview
@Composable
fun CommentsListItemPreview() {
    SocialAppTheme {
        CommentsListItem(
            comment = sampleComments.random(),
            onProfileClick = {

            },
            onMoreIconClick = {

            }
        )
    }
}

@Composable
fun CommentsListItem(
    modifier: Modifier = Modifier,
    comment: Comment,
    onProfileClick: (Int) -> Unit,
    onMoreIconClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(LargeSpacing),
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {

        CircularImage(
            modifier = modifier.size(30.dp),
            imageUrl = comment.authImageUrl
        ) {
            onProfileClick(comment.authId)
        }

        Column(
            modifier = modifier.weight(1f)
        ) {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
            ) {
                Text(
                    modifier = modifier.alignByBaseline(),
                    text = comment.authName,
                    style = MaterialTheme.typography.subtitle2,
                )

                Text(
                    modifier = modifier
                        .alignByBaseline()
                        .weight(1f),
                    text = comment.date,
                    style = MaterialTheme.typography.caption,
                    color = if (MaterialTheme.colors.isLight) {
                        Color.LightGray
                    } else Color.DarkGray
                )
                Icon(
                    painter = painterResource(id = R.drawable.round_more_horiz_24),
                    contentDescription = null,
                    tint = if (MaterialTheme.colors.isLight) {
                        Color.LightGray
                    } else Color.DarkGray,
                    modifier = modifier.clickable { onMoreIconClick() }
                )
            }

            Text(
                text = comment.comment,
                style = MaterialTheme.typography.body2
            )
        }
    }
}