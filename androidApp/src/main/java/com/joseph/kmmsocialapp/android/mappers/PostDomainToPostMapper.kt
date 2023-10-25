package com.joseph.kmmsocialapp.android.mappers

import com.joseph.kmmsocialapp.android.common.datastore.UserDataStore
import com.joseph.kmmsocialapp.android.common.extensions.formatAsDayMonthYear
import com.joseph.kmmsocialapp.android.common.fake_data.Post
import com.joseph.kmmsocialapp.domain.models.PostDomain
import java.util.Date

interface PostDomainToPostMapper {

    suspend fun map(from: PostDomain): Post
}

class PostDomainToPostMapperImpl(
    private val userDataStore: UserDataStore
) : PostDomainToPostMapper {

    override suspend fun map(from: PostDomain): Post = from.run {
        val currentUserId = userDataStore.fetchUserId()
        when {
            imageUrls.isEmpty() -> Post.TextPost(
                id = id.toString(),
                text = message ?: "",
                createdAt = Date(releaseDate).formatAsDayMonthYear(),
                likesCount = 0,
                commentCount = 0,
                authorId = user.id ?: 0,
                authorName = user.name ?: "",
                authorImage = user.userImage ?: "",
                authorLastName = user.lastName ?: "",
                isLiked = false,
                isOwnPost = currentUserId == user.id,
            )

            else -> Post.PhotoPost(
                id = id.toString(),
                text = message ?: "",
                createdAt = Date(releaseDate).formatAsDayMonthYear(),
                likesCount = 0,
                commentCount = 0,
                authorId = user.id ?: 0,
                authorName = user.name ?: "",
                authorImage = user.userImage ?: "",
                authorLastName = user.lastName ?: "",
                isLiked = false,
                isOwnPost = currentUserId == user.id,
                imageUrls = imageUrls
            )
        }
    }
}