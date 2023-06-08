package com.joseph.kmmsocialapp.data.mappers

import com.joseph.kmmsocialapp.common.mapper.Mapper
import com.joseph.kmmsocialapp.data.models.AuthResponseData
import com.joseph.kmmsocialapp.domain.models.AuthResultData

internal class AuthResponseDataToAuthResultDataMapper : Mapper<AuthResponseData, AuthResultData> {

    override fun map(from: AuthResponseData): AuthResultData = from.run {
        AuthResultData(
            id = id,
            name = name,
            bio = bio,
            avatar = avatar,
            token = token,
            followersCount = followersCount,
            followingCount = followingCount
        )
    }
}