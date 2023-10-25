package com.joseph.kmmsocialapp.data.mappers

import com.joseph.kmmsocialapp.common.data.BASE_URL
import com.joseph.kmmsocialapp.common.mapper.Mapper
import com.joseph.kmmsocialapp.data.models.PostCloud
import com.joseph.kmmsocialapp.domain.models.PostDomain
import com.joseph.kmmsocialapp.domain.models.PostUserDomain

class PostCloudToPostDomainMapper : Mapper<PostCloud, PostDomain> {

    override fun map(from: PostCloud): PostDomain = from.run {
        PostDomain(
            commentsCount = commentsCount,
            id = id,
            imageUrls = imageUrls.map { imageUrl ->
                if (imageUrl.isBlank()) String()
                else BASE_URL + imageUrl
            },
            likesCount = likesCount,
            message = message,
            releaseDate = releaseDate,
            savedCount = savedCount,
            user = PostUserDomain(
                id = user.id,
                userImage = if (user.userImage == null) null
                else BASE_URL + user.userImage,
                name = user.name,
                lastName = user.lastName
            )
        )
    }
}