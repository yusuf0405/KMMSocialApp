package com.joseph.kmmsocialapp.common.util.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * The launchSafe function is an extension of the [CoroutineScope.launch()]
 * function and allows launching coroutines with additional exception handling
 *
 * @param safeAction - suspend function that will be executed safely within a coroutine.
 *
 * @param onError - lambda function that will be invoked in case an exception is thrown while
 * executing the [safeAction]. By default, this lambda function is set to log the error using [Timber::e]
 */
inline fun CoroutineScope.launchSafe(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    crossinline onError: (Throwable) -> Unit = { println(it) },
    crossinline safeAction: suspend CoroutineScope.() -> Unit,
) = this.launch(dispatcher) {
    try {
        safeAction()
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        onError(e)
    }
}

inline fun <T> Flow<T>.onError(crossinline action: (Throwable) -> Unit) = this.catch { action(it) }