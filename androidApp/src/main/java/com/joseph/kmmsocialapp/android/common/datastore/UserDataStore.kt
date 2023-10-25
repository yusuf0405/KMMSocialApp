package com.joseph.kmmsocialapp.android.common.datastore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UserDataStore {

    fun loginOutFlow(): StateFlow<Boolean>

    suspend fun fetchUser(): UserPreferences

    fun fetchUserFlow(): Flow<UserPreferences>

    suspend fun fetchUserId(): Int

    fun fetchUserIdFlow(): Flow<Int>
}