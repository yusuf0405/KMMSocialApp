package com.joseph.kmmsocialapp.domain.models

data class UserInfoDomain(
    val id: Int,
    val name: String,
    val lastName: String,
    val avatar: String?,
    val releaseDate: Long,
)
