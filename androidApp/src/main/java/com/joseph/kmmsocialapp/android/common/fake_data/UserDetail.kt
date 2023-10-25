package com.joseph.kmmsocialapp.android.common.fake_data

import java.util.UUID

data class UserDetails(
    val id: String,
    val name: String,
    val lastName: String,
    val profileUrl: String,
    val profileBackgroundUrl: String,
    val isFollowing: Boolean = false,
    val isYour: Boolean = false,
    val education: String,
    val aboutYou: String,
    val followersCount: Int,
    val followingCount: Int,
) {
    fun fullName() = "$name $lastName"
}

val sampleUserDetail = listOf(
    UserDetails(
        id = UUID.randomUUID().hashCode().toString(),
        name = "Lana",
        lastName = "Del Rey",
        profileUrl = "https://avatars.mds.yandex.net/i?id=4fb92a338be9446b077add616c071432b247ed99-10932937-images-thumbs&n=13",
        profileBackgroundUrl = "https://i.pinimg.com/originals/5d/46/69/5d4669b1dd14a37d3d6cb963b54b906b.jpg",
        isFollowing = false,
        isYour = false,
        education = "Brooklyn, NY",
        aboutYou = "Writer by Profession. Artist by Passion!",
        followersCount = 200,
        followingCount = 150
    )
)
