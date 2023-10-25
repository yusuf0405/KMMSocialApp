package com.joseph.kmmsocialapp.common.util.coroutines

import com.joseph.kmmsocialapp.common.util.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class IosDispatcherProvider : DispatcherProvider {

    override val io: CoroutineDispatcher
        get() = Dispatchers.Default

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

}

internal actual fun provideDispatcher(): DispatcherProvider = IosDispatcherProvider()