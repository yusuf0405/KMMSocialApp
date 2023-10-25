package com.joseph.kmmsocialapp.android.mappers

import com.joseph.kmmsocialapp.android.common.datastore.UserPreferences
import com.joseph.kmmsocialapp.common.mapper.Mapper
import com.joseph.kmmsocialapp.domain.models.AuthResultData

class AuthResultDataToUserPreferencesMapper : Mapper<AuthResultData, UserPreferences> {
    override fun map(from: AuthResultData): UserPreferences = from.run {
        UserPreferences(
            id = id,
            name = name,
            bio = bio,
            avatar = avatar,
            token = token,
        )
    }
}