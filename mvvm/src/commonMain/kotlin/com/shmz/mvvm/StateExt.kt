package com.shmz.mvvm

/**
 * Create a [ShareableState] to observe state changes.
 */
expect fun <T : Any> StateHolder.saveableState(initialValue: T): ShareableState<T>
