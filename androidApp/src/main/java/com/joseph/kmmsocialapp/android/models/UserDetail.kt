package com.joseph.kmmsocialapp.android.models

import java.util.Date

data class UserDetail(
    val id: Int,
    val name: String,
    val lastName: String,
    val bio: String?,
    val avatar: String?,
    val profileBackground: String?,
    val education: String?,
    val releaseDate: Date,
    val followersCount: Int,
    val followingCount: Int
) {
    fun fullName() = "$name $lastName"

    companion object {

        val unknown = UserDetail(
            id = -1,
            name = "Unknown",
            lastName = "",
            bio = "",
            avatar = "",
            profileBackground = "",
            education = "",
            releaseDate = Date(),
            followersCount = 0,
            followingCount = 0
        )
    }
}