package com.joseph.kmmsocialapp.android.common.components.items

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.components.CircularImage
import com.joseph.kmmsocialapp.android.common.fake_data.Comment
import com.joseph.kmmsocialapp.android.common.fake_data.sampleComments
import com.joseph.kmmsocialapp.android.common.theme.LargeSpacing
import com.joseph.kmmsocialapp.android.common.theme.MediumSpacing
import com.joseph.kmmsocialapp.android.common.theme.SmallSpacing
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme

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
                    color = MaterialTheme.colors.onBackground
                )

                Text(
                    modifier = modifier
                        .alignByBaseline()
                        .weight(1f),
                    text = comment.date,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.secondary
                )
                Icon(
                    painter = painterResource(id = R.drawable.round_more_horiz_24),
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondary,
                    modifier = modifier.clickable { onMoreIconClick() }
                )
            }

            Text(
                modifier = Modifier.padding(top = SmallSpacing),
                text = comment.comment,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

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