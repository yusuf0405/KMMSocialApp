package com.joseph.kmmsocialapp.data.mappers

import com.joseph.kmmsocialapp.common.mapper.Mapper
import com.joseph.kmmsocialapp.data.models.UserDetailCloud
import com.joseph.kmmsocialapp.domain.models.UserDetailDomain

internal class UserDetailCloudToUserDetailDomainMapper : Mapper<UserDetailCloud, UserDetailDomain> {

    override fun map(from: UserDetailCloud): UserDetailDomain = from.run {
        UserDetailDomain(
            id = id,
            name = name,
            lastName = lastName,
            avatar = avatar,
            releaseDate = releaseDate,
            bio = bio,
            profileBackground = profileBackground,
            education = education,
            followingCount = followingCount,
            followersCount = followersCount
        )
    }
}