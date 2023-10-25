package com.joseph.kmmsocialapp.android.common.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class UserDataStoreImpl(
    private val dataStore: DataStore<UserPreferences>
) : UserDataStore {

    private val loginOutFlow = MutableStateFlow(false)

    override fun loginOutFlow(): StateFlow<Boolean> {
        return loginOutFlow.asStateFlow()
    }

    override suspend fun fetchUser(): UserPreferences {
        val user = dataStore.data.firstOrNull()
        return if (user == null) {
            loginOutFlow.tryEmit(true)
            UserPreferences.unknown
        } else user

    }

    override fun fetchUserFlow(): Flow<UserPreferences> = dataStore.data

    override suspend fun fetchUserId(): Int {
        val userId = dataStore.data.firstOrNull()?.id
        return if (userId == null) {
            loginOutFlow.tryEmit(true)
            -1
        } else userId
    }

    override fun fetchUserIdFlow(): Flow<Int> = dataStore.data.map { it.id }
}