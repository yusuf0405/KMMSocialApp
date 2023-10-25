package com.joseph.kmmsocialapp.android.models

import java.util.Date

data class UserInfo(
    val id: Int,
    val name: String,
    val lastName: String,
    val avatar: String?,
    val releaseDate: Date,
) {

    companion object {

        val preview = UserInfo(
            id = 1,
            name = "Joseph",
            lastName = "Barbera",
            avatar = "https://clipart-library.com/images/pTqre6K8c.jpg",
            releaseDate = Date()
        )
    }
}
