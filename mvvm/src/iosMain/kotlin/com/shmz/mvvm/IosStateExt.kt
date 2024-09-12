package com.shmz.mvvm

/**
 * Create a [ShareableState] to observe state changes.
 */
actual fun <T : Any> StateHolder.saveableState(initialValue: T): ShareableState<T> =
    ShareableState(initialValue) { binder.objectWillChange() }
