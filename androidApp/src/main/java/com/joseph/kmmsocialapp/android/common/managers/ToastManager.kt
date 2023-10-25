package com.joseph.kmmsocialapp.android.common.managers

import android.util.Log
import com.joseph.kmmsocialapp.android.common.extensions.createMutableSharedFlowAsLiveData
import com.joseph.kmmsocialapp.android.common.extensions.createMutableSharedFlowAsSingleLiveEvent
import com.joseph.kmmsocialapp.android.models.IdResourceString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach

class ToastManager : ToastDisplayFlowManager, ToastDisplayManager {

    private val toastFlow = createMutableSharedFlowAsLiveData<IdResourceString>()

    override fun toastFlow(): Flow<IdResourceString> {
        return toastFlow
            .onEach {
                Log.i("Joseph","onEach")
            }
//            .asSharedFlow()
    }

    override fun setToast(message: IdResourceString) {
        toastFlow.tryEmit(message)
    }
}