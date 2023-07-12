package com.joseph.kmmsocialapp.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.kmmsocialapp.android.common.fake_data.FollowsUser
import com.joseph.kmmsocialapp.android.common.fake_data.Post
import com.joseph.kmmsocialapp.android.common.fake_data.samplePosts
import com.joseph.kmmsocialapp.android.common.fake_data.sampleUsers
import com.joseph.kmmsocialapp.android.home.onboarding.OnBoardingUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class PostUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val errorMessage: String? = null
)

class HomeViewModel : ViewModel() {
    var postUiState by mutableStateOf(PostUiState())
        private set

    var onBoardingState by mutableStateOf(OnBoardingUiState())
        private set

    init {
        fetchData()
    }

    private fun fetchData() {
        onBoardingState = onBoardingState.copy(isLoading = true)
        postUiState = postUiState.copy(isLoading = true)

        viewModelScope.launch {
            delay(1000)
            onBoardingState = onBoardingState.copy(
                isLoading = false,
                users = sampleUsers
            )
            postUiState = postUiState.copy(
                isLoading = false,
                posts = samplePosts
            )
        }
    }

    fun onUserClick(followsUser: FollowsUser) {

    }

    fun onFollowButtonClick(isFollow: Boolean, followsUser: FollowsUser) {

    }

    fun onBoardingFinish() {

    }

    fun onProfileClick(userId: Int) {

    }

    fun onLikeClick() {

    }

    fun onCommentClick() {

    }

    fun fetchMoreData() {

    }
}














