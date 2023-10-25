package com.joseph.kmmsocialapp.android.common.provider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProviderImpl : CoroutineDispatcherProvider {

    override val io: CoroutineDispatcher = Dispatchers.IO

    override val main: CoroutineDispatcher = Dispatchers.Main
}