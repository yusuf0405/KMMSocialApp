package com.joseph.kmmsocialapp.android.presentation.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.kmmsocialapp.android.common.datastore.UserDataStore
import com.joseph.kmmsocialapp.android.common.managers.ToastDisplayFlowManager
import com.joseph.kmmsocialapp.common.util.coroutines.launchSafe
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel(
    private val userDataStore: UserDataStore,
    private val toastDisplayFlowManager: ToastDisplayFlowManager
) : ViewModel() {

    val toastFlow = toastDisplayFlowManager.toastFlow()

    val authState: StateFlow<Boolean> = userDataStore.loginOutFlow()

    val userIdFlow = userDataStore.fetchUserIdFlow()
}