package com.joseph.kmmsocialapp.android.presentation.screens.profile

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.fake_data.samplePhotoPosts
import com.joseph.kmmsocialapp.android.common.fake_data.sampleTextPosts
import com.joseph.kmmsocialapp.android.mappers.PostDomainToPostMapper
import com.joseph.kmmsocialapp.android.mappers.UserDetailDomainToUserDetailMapper
import com.joseph.kmmsocialapp.android.models.UserDetail
import com.joseph.kmmsocialapp.android.models.UserInfo
import com.joseph.kmmsocialapp.android.presentation.screens.home.PostActions
import com.joseph.kmmsocialapp.android.presentation.screens.profile.models.ProfileTab
import com.joseph.kmmsocialapp.android.presentation.screens.profile.tabs.posts.ProfilePostsScreen
import com.joseph.kmmsocialapp.android.presentation.screens.profile.tabs.posts.ProfilePostsUiState
import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.common.util.coroutines.launchSafe
import com.joseph.kmmsocialapp.data.repository.defaultErrorMessage
import com.joseph.kmmsocialapp.domain.usecases.post.FetchUserPostsUseCase
import com.joseph.kmmsocialapp.domain.usecases.user.FetchUserByIdUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(
    private val userId: Int,
    private val fetchUserPostsUseCase: FetchUserPostsUseCase,
    private val postDomainToPostMapper: PostDomainToPostMapper,
    private val fetchUserByIdUseCase: FetchUserByIdUseCase,
    private val userDetailDomainToUserDetailMapper: UserDetailDomainToUserDetailMapper,
) : ViewModel(), PostActions {

    private val _profileUiStateFlow = MutableStateFlow(ProfileUiState())
    val profileUiStateFlow: StateFlow<ProfileUiState> = _profileUiStateFlow.asStateFlow()

    private val postsUiStateFlow = MutableStateFlow(ProfilePostsUiState())

    private var profileUiState = _profileUiStateFlow.value

    init {
        viewModelScope.launchSafe {
            fetchUserInfo()
            fetchUserPosts()
        }
    }

    private suspend fun fetchUserInfo() {
        _profileUiStateFlow.tryEmit(ProfileUiState(isLoading = true))
        when (val response = fetchUserByIdUseCase(userId)) {
            is Result.Success -> {
                val userDomain = response.data
                profileUiState = if (userDomain != null) profileUiState.copy(
                    userDetail = userDetailDomainToUserDetailMapper.map(userDomain),
                    isLoading = false,
                    tabs = fetchProfileTabs(),
                    isCurrentUser = userDomain.id == userId
                ) else profileUiState.copy(
                    isLoading = true,
                    errorMessage = response.message ?: defaultErrorMessage,
                    // Временно для тестов
                    userDetail = UserDetail.unknown
                )

                _profileUiStateFlow.tryEmit(profileUiState)
            }

            is Result.Error -> {
                _profileUiStateFlow.tryEmit(
                    profileUiState.copy(
                        errorMessage = response.message ?: String(),
                        isLoading = false,
                        // Временно для тестов
                        userDetail = UserDetail.unknown
                    )
                )
            }
        }
    }

    private suspend fun fetchUserPosts() {
        postsUiStateFlow.tryEmit(ProfilePostsUiState(isLoading = true))
        delay(2000)
        when (val response = fetchUserPostsUseCase(userId)) {
            is Result.Success -> {
                val posts = response.data?.map { postDomainToPostMapper.map(it) }
                postsUiStateFlow.tryEmit(
                    ProfilePostsUiState(
                        posts = posts ?: emptyList(),
                        isLoading = false
                    )
                )
            }

            is Result.Error -> {
                postsUiStateFlow.tryEmit(
                    ProfilePostsUiState(
                        errorMessage = response.message ?: String(),
                        isLoading = false,
                        // Временно для тестов
                        posts = (sampleTextPosts + samplePhotoPosts).shuffled()
                    )
                )
            }
        }
    }

    private fun fetchProfileTabs() = listOf(
        ProfileTab(
            titleId = R.string.posts,
            content = { CreateProfilePostsScreen() }
        ),
        ProfileTab(
            titleId = R.string.stories,
            content = {}
        ),
        ProfileTab(
            titleId = R.string.liked,
            content = {}
        ),
        ProfileTab(
            titleId = R.string.tagged,
            content = {}
        ),
    )

    @Composable
    private fun CreateProfilePostsScreen() {
        ProfilePostsScreen(
            uiStateFlow = postsUiStateFlow.asStateFlow(),
            actions = this,
            refreshData = { viewModelScope.launchSafe { fetchUserPosts() } }
        )
    }

    override fun onUserClick(user: UserInfo) {

    }

    override fun onFollowButtonClick(isFollow: Boolean, user: UserInfo) {

    }

    override fun onBoardingFinish() {

    }

    override fun onStoriesClick() {

    }

    override fun onLikeClick() {

    }

    override fun onCommentClick() {

    }

    override fun onPostClick(postId: String) {
    }

    override fun onProfileClick(userId: Int) {

    }
}