package com.joseph.kmmsocialapp.android.presentation.screens.profile.tabs.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joseph.kmmsocialapp.android.common.components.items.postList
import com.joseph.kmmsocialapp.android.common.theme.ExtraLargeSpacing
import com.joseph.kmmsocialapp.android.presentation.screens.home.PostActions
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ProfilePostsScreen(
    uiStateFlow: StateFlow<ProfilePostsUiState>,
    refreshData: () -> Unit,
    actions: PostActions,
) {
    val uiState = uiStateFlow.collectAsStateWithLifecycle().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        if (uiState.posts.isNotEmpty()) {
            LazyColumn {
                postList(
                    posts = uiState.posts,
                    actions = actions,
                )
            }
        }
        if (uiState.isLoading) {
            Column(
                modifier = Modifier
                    .padding(top = ExtraLargeSpacing)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        if (uiState.errorMessage.isNotEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = uiState.errorMessage,
                    style = MaterialTheme.typography.body2
                )
                Button(
                    onClick = { refreshData() }
                ) {
                    Text(
                        text = "Retry",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}