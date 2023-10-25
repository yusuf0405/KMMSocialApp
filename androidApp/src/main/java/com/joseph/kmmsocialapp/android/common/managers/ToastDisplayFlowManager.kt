package com.joseph.kmmsocialapp.android.common.managers

import com.joseph.kmmsocialapp.android.models.IdResourceString
import kotlinx.coroutines.flow.Flow

interface ToastDisplayFlowManager {

    fun toastFlow(): Flow<IdResourceString>
}