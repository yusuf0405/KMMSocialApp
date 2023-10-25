package com.joseph.kmmsocialapp.android.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.kmmsocialapp.android.common.datastore.UserDataStore
import com.joseph.kmmsocialapp.android.common.extensions.createMutableSharedFlowAsSingleLiveEvent
import com.joseph.kmmsocialapp.android.common.fake_data.samplePhotoPosts
import com.joseph.kmmsocialapp.android.common.fake_data.sampleTextPosts
import com.joseph.kmmsocialapp.android.mappers.UserInfoDomainToUserInfoMapper
import com.joseph.kmmsocialapp.android.models.UserInfo
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.PostDetailDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.ProfileDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.StoriesDestination
import com.joseph.kmmsocialapp.android.presentation.screens.home.onboarding.OnBoardingUiState
import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.common.util.coroutines.launchSafe
import com.joseph.kmmsocialapp.domain.usecases.onboarding.FetchOnboardingUsersUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val fetchOnboardingUsersUseCase: FetchOnboardingUsersUseCase,
    private val userInfoDomainToUserInfoMapper: UserInfoDomainToUserInfoMapper,
    private val userDataStore: UserDataStore,
) : ViewModel(), PostActions {

    private val _postsUiStateFlow = MutableStateFlow(PostsUiState())
    val postsUiStateFlow: StateFlow<PostsUiState> = _postsUiStateFlow.asStateFlow()

    private val _onBoardingUiStateFlow = MutableStateFlow(OnBoardingUiState())
    val onBoardingUiStateFlow: StateFlow<OnBoardingUiState> = _onBoardingUiStateFlow.asStateFlow()

    private val _navCommandFlow = createMutableSharedFlowAsSingleLiveEvent<String>()
    val navCommandFlow: SharedFlow<String> = _navCommandFlow.asSharedFlow()

    private var postUiState = _postsUiStateFlow.value
    private var onBoardingState = _onBoardingUiStateFlow.value

    private var currentUserId: Int? = null

    init {
        viewModelScope.launchSafe {
            currentUserId = userDataStore.fetchUserId()
            fetchUserPosts()
            fetchOnboardingUsersUseCase()
        }
    }

    private suspend fun fetchUserPosts() {
        _postsUiStateFlow.tryEmit(postUiState.copy(isLoading = true))
        delay(1000)
        val posts = (samplePhotoPosts + sampleTextPosts).toSet().toList()
        _postsUiStateFlow.tryEmit(postUiState.copy(isLoading = false, posts = posts))

    }

    private suspend fun fetchOnboardingUsersUseCase() {
        val currentUserId = currentUserId ?: return
        when (val result = fetchOnboardingUsersUseCase(currentUserId)) {
            is Result.Success -> {
                val userDomains = result.data
                onBoardingState = if (userDomains == null) onBoardingState.copy(
                    errorMessage = result.message
                ) else if (userDomains.isEmpty()) onBoardingState.copy(
                    shouldShowOnBoarding = false,
                )
                else onBoardingState.copy(
                    users = userDomains.map(userInfoDomainToUserInfoMapper::map),
                    shouldShowOnBoarding = true,
                )
            }

            is Result.Error -> {
                onBoardingState = onBoardingState.copy(
                    errorMessage = result.message,
                    // Временно для тестов
                    users = listOf(UserInfo.preview, UserInfo.preview, UserInfo.preview),
                    shouldShowOnBoarding = true
                )
            }
        }
        _onBoardingUiStateFlow.tryEmit(onBoardingState)
    }

    override fun onUserClick(user: UserInfo) {
        _navCommandFlow.tryEmit(ProfileDestination(user.id).route)
    }

    override fun onFollowButtonClick(isFollow: Boolean, user: UserInfo) {

    }

    override fun onBoardingFinish() {
        _onBoardingUiStateFlow.tryEmit(onBoardingState.copy(shouldShowOnBoarding = false))
    }

    override fun onStoriesClick() {
        _navCommandFlow.tryEmit(StoriesDestination().route)
    }

    override fun onProfileClick(userId: Int) {

    }

    override fun onLikeClick() {

    }

    override fun onCommentClick() {

    }

    override fun onPostClick(postId: String) {
        _navCommandFlow.tryEmit(PostDetailDestination(postId).route)
    }

    fun fetchMoreData() {

    }
}