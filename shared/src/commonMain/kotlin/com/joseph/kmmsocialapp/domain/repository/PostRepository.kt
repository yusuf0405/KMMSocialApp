package com.joseph.kmmsocialapp.domain.repository

import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.domain.models.PostDomain

internal interface PostRepository {

    suspend fun addPost(
        byteArray: List<ByteArray?>,
        message: String?,
        userId: Int,
    ): Result<PostDomain>

    suspend fun fetchUserPosts(userId: Int): Result<List<PostDomain>>

    suspend fun fetchRecommendedPosts(
        page: Int,
        pageSize: Int,
        userId: Int
    ): Result<List<PostDomain>>
}
