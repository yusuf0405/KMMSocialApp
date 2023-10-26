package com.joseph.kmmsocialapp.android.common.managers

import com.joseph.kmmsocialapp.android.common.extensions.createMutableSharedFlowAsLiveData
import com.joseph.kmmsocialapp.android.models.IdResourceString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asSharedFlow

class ToastManager : ToastDisplayFlowManager, ToastDisplayManager {

    private val toastFlow = createMutableSharedFlowAsLiveData<IdResourceString>()

    override fun toastFlow(): Flow<IdResourceString> {
        return toastFlow.asSharedFlow()
    }

    override fun setToast(message: IdResourceString) {
        toastFlow.tryEmit(message)
    }
}