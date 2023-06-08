package com.joseph.kmmsocialapp.domain.models

data class AuthResultData(
    val id: Int,
    val name: String,
    val bio: String,
    val avatar: String? = null,
    val token: String,
    val followersCount: Int = 0,
    val followingCount: Int = 0,
)