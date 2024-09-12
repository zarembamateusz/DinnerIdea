package com.shmz.mvvm

/**
 * [ShareableState] representations that additionally implement mechanism to save and restore instance state.
 * That helps to handle system-initiated process death.
 */
expect class SaveableShareableState<T : Any> : ShareableState<T>
