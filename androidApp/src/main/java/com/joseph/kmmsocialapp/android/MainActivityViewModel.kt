package com.joseph.kmmsocialapp.android

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import com.joseph.kmmsocialapp.android.common.datastore.UserPreferences
import kotlinx.coroutines.flow.map

class MainActivityViewModel(
    private val dataStore: DataStore<UserPreferences>
) : ViewModel() {

    val authState = dataStore.data.map { it.token }
}