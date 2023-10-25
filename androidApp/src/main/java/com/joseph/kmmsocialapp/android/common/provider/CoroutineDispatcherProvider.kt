package com.joseph.kmmsocialapp.android.common.provider

import kotlinx.coroutines.CoroutineDispatcher


interface CoroutineDispatcherProvider {

    val io: CoroutineDispatcher

    val main: CoroutineDispatcher
}