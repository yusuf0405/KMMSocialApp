package com.joseph.kmmsocialapp.android.mappers

import com.joseph.kmmsocialapp.android.models.UserInfo
import com.joseph.kmmsocialapp.common.mapper.Mapper
import com.joseph.kmmsocialapp.domain.models.UserInfoDomain
import java.util.Date

class UserInfoDomainToUserInfoMapper : Mapper<UserInfoDomain, UserInfo> {

    override fun map(from: UserInfoDomain): UserInfo = from.run {
        UserInfo(
            id = id,
            name = name,
            lastName = lastName,
            avatar = avatar,
            releaseDate = Date(releaseDate)
        )
    }
}