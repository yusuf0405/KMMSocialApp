package com.joseph.kmmsocialapp.android.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.kmmsocialapp.android.common.datastore.UserDataStore
import com.joseph.kmmsocialapp.android.common.extensions.createMutableSharedFlowAsSingleLiveEvent
import com.joseph.kmmsocialapp.android.mappers.PostDomainToPostMapper
import com.joseph.kmmsocialapp.android.mappers.UserInfoDomainToUserInfoMapper
import com.joseph.kmmsocialapp.android.models.UserInfo
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.PostDetailDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.ProfileDestination
import com.joseph.kmmsocialapp.android.presentation.screens.destinations.StoriesDestination
import com.joseph.kmmsocialapp.android.presentation.screens.home.onboarding.OnBoardingUiState
import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.common.util.coroutines.launchSafe
import com.joseph.kmmsocialapp.data.repository.defaultErrorMessage
import com.joseph.kmmsocialapp.domain.models.PostDomain
import com.joseph.kmmsocialapp.domain.usecases.onboarding.FetchOnboardingUsersUseCase
import com.joseph.kmmsocialapp.domain.usecases.post.FetchRecommendedPostsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.atomic.AtomicInteger

private const val DEFAULT_PAGE = 1
private const val DEFAULT_PAGE_SIZE = 5

class HomeViewModel(
    private val fetchOnboardingUsersUseCase: FetchOnboardingUsersUseCase,
    private val fetchRecommendedPostsUseCase: FetchRecommendedPostsUseCase,
    private val userInfoDomainToUserInfoMapper: UserInfoDomainToUserInfoMapper,
    private val postDomainToPostMapper: PostDomainToPostMapper,
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
    private var currentPage = AtomicInteger(DEFAULT_PAGE)

    init {
        viewModelScope.launchSafe {
            currentUserId = coroutineScope { async { userDataStore.fetchUserId() }.await() }
            fetchUserPosts()
            fetchOnboardingUsersUseCase()
        }
    }

    fun fetchUserPosts(forceLoad: Boolean = false) {
        if (postUiState.isLoading) return
        if (forceLoad) currentPage.set(DEFAULT_PAGE)

        if (currentPage.get() == DEFAULT_PAGE) {
            _postsUiStateFlow.tryEmit(postUiState.copy(refreshing = true))
        }

        val currentUserId = currentUserId ?: return
        viewModelScope.launchSafe {
            _postsUiStateFlow.tryEmit(postUiState.copy(isLoading = true))
            val response = fetchRecommendedPostsUseCase(
                page = currentPage.get(),
                pageSize = DEFAULT_PAGE_SIZE,
                userId = currentUserId
            )
            delay(2000)
            postUiState = when (response) {
                is Result.Success -> handleSuccessResponse(response)
                is Result.Error -> handleErrorResponse(response)
            }
            _postsUiStateFlow.tryEmit(postUiState)
        }
    }

    private suspend fun handleSuccessResponse(
        response: Result<List<PostDomain>>
    ) = response.data?.let { postsDomain ->
        val posts = postsDomain.map { postDomainToPostMapper.map(it) }
        val allPosts = if (currentPage.get() == 1) posts else postUiState.posts + posts
        currentPage.incrementAndGet()
        postUiState.copy(
            isLoading = false,
            refreshing = false,
            loadFinished = posts.isEmpty(),
            posts = allPosts
        )
    } ?: postUiState

    private fun handleErrorResponse(response: Result<List<PostDomain>>) = postUiState.copy(
        isLoading = false,
        refreshing = false,
        loadFinished = true,
        errorMessage = response.message ?: defaultErrorMessage
    )

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
}