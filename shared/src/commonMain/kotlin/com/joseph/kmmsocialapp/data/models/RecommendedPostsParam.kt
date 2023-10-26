package com.joseph.kmmsocialapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class RecommendedPostsParam(
    val page: Int,
    val pageSize: Int,
    val userId: Int
)