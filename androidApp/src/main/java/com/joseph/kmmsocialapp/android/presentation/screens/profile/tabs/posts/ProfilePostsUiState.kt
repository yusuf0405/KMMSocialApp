package com.joseph.kmmsocialapp.android.presentation.screens.profile.tabs.posts

import com.joseph.kmmsocialapp.android.common.fake_data.Post

data class ProfilePostsUiState(
    val posts: List<Post> = emptyList(),
    val errorMessage: String = String(),
    val isLoading: Boolean = false
)