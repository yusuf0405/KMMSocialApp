package com.joseph.kmmsocialapp.android.common.datastore

import com.joseph.kmmsocialapp.domain.models.AuthResultData
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val id: Int = -1,
    val name: String = String(),
    val bio: String = String(),
    val avatar: String? = null,
    val token: String = String(),
    val followersCount: Int = 0,
    val followingCount: Int = 0,
)

fun UserPreferences.toAuthResultData(): AuthResultData {
    return AuthResultData(
        id = id,
        name = name,
        bio = bio,
        avatar = avatar,
        token = token,
        followersCount = followersCount,
        followingCount = followingCount
    )
}