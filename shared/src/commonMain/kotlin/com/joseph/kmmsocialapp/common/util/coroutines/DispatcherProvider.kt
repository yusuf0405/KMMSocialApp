package com.joseph.kmmsocialapp.common.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher

internal interface DispatcherProvider {

    val io: CoroutineDispatcher

    val main: CoroutineDispatcher
}

internal expect fun provideDispatcher(): DispatcherProvider