package com.joseph.kmmsocialapp.android.presentation.screens.home

import com.joseph.kmmsocialapp.android.models.UserInfo

interface PostActions {

    fun onUserClick(user: UserInfo)

    fun onFollowButtonClick(isFollow: Boolean, user: UserInfo)

    fun onBoardingFinish()

    fun onStoriesClick()

    fun onLikeClick()

    fun onCommentClick()

    fun onPostClick(postId: String)

    fun onProfileClick(userId: Int)

    object Preview : PostActions {
        override fun onUserClick(user: UserInfo) = Unit
        override fun onFollowButtonClick(isFollow: Boolean, user: UserInfo) = Unit
        override fun onBoardingFinish() = Unit
        override fun onStoriesClick() = Unit
        override fun onLikeClick() = Unit
        override fun onCommentClick() = Unit
        override fun onPostClick(postId: String) = Unit
        override fun onProfileClick(userId: Int) = Unit
    }
}