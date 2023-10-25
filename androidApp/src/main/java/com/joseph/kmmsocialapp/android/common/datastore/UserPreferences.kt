package com.joseph.kmmsocialapp.android.common.datastore

import com.joseph.kmmsocialapp.domain.models.AuthResultData
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val id: Int = -1,
    val name: String = String(),
    val lastName: String = String(),
    val bio: String? = null,
    val avatar: String? = null,
    val token: String = String(),
) {

    companion object {
        val unknown = UserPreferences(
            id = -1,
            name = String(),
            bio = String(),
            avatar = null,
            token = String(),
        )
    }
}

fun UserPreferences.toAuthResultData(): AuthResultData {
    return AuthResultData(
        id = id,
        name = name,
        bio = bio,
        avatar = avatar,
        token = token,
        lastName = lastName
    )
}