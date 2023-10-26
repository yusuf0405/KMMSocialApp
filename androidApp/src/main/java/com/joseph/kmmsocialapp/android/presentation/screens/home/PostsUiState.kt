package com.joseph.kmmsocialapp.android.presentation.screens.home

import com.joseph.kmmsocialapp.android.common.fake_data.Post

data class PostsUiState(
    val isLoading: Boolean = false,
    val refreshing: Boolean = true,
    val loadFinished: Boolean = false,
    val posts: List<Post> = emptyList(),
    val errorMessage: String? = null
)