package com.joseph.kmmsocialapp.android.presentation.screens.post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.kmmsocialapp.android.common.fake_data.Comment
import com.joseph.kmmsocialapp.android.common.fake_data.Post
import com.joseph.kmmsocialapp.android.common.fake_data.sampleComments
import com.joseph.kmmsocialapp.android.common.fake_data.samplePhotoPosts
import com.joseph.kmmsocialapp.android.common.fake_data.sampleTextPosts
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostDetailScreenViewModel : ViewModel() {

    var postDetailUiState by mutableStateOf(PostDetailUiState())
    var commentsUiState by mutableStateOf(CommentsUiState())

    fun fetchData(postId: String) {
        viewModelScope.launch {
            postDetailUiState = postDetailUiState.copy(
                isLoading = true
            )
            commentsUiState = commentsUiState.copy(
                isLoading = true
            )
            delay(1000)
            postDetailUiState = postDetailUiState.copy(
                isLoading = false,
                post = (sampleTextPosts + samplePhotoPosts).find { it.storiesId == postId }
            )
            commentsUiState = commentsUiState.copy(
                isLoading = false,
                comments = sampleComments
            )
        }
    }

    fun onCommentMoreIconClick(comment: Comment) {

    }

    fun onProfileClick(authId: Int) {


    }

    fun onAddCommentClick() {

    }
}


data class PostDetailUiState(
    val isLoading: Boolean = false,
    val post: Post? = null,
    val errorMessage: String? = null
)

data class CommentsUiState(
    val isLoading: Boolean = false,
    val comments: List<Comment> = listOf(),
    val errorMessage: String? = null
)
