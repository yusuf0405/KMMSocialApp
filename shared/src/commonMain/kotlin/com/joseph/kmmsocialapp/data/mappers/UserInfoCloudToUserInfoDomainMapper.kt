package com.joseph.kmmsocialapp.data.mappers

import com.joseph.kmmsocialapp.common.mapper.Mapper
import com.joseph.kmmsocialapp.data.models.AuthResponseData
import com.joseph.kmmsocialapp.data.models.UserInfoCloud
import com.joseph.kmmsocialapp.domain.models.AuthResultData
import com.joseph.kmmsocialapp.domain.models.UserInfoDomain

internal class UserInfoCloudToUserInfoDomainMapper : Mapper<UserInfoCloud, UserInfoDomain> {

    override fun map(from: UserInfoCloud): UserInfoDomain = from.run {
        UserInfoDomain(
            id = id,
            name = name,
            lastName = lastName,
            avatar = avatar,
            releaseDate = releaseDate
        )
    }
}