package com.joseph.kmmsocialapp.android.mappers

import com.joseph.kmmsocialapp.android.models.UserDetail
import com.joseph.kmmsocialapp.common.mapper.Mapper
import com.joseph.kmmsocialapp.domain.models.UserDetailDomain
import java.util.Date

class UserDetailDomainToUserDetailMapper : Mapper<UserDetailDomain, UserDetail> {

    override fun map(from: UserDetailDomain): UserDetail = from.run {
        UserDetail(
            id = id,
            name = name,
            lastName = lastName,
            avatar = avatar,
            releaseDate = Date(releaseDate),
            bio = bio,
            profileBackground = profileBackground,
            education = education,
            followersCount = followersCount,
            followingCount = followingCount
        )
    }
}