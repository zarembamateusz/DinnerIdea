package com.shmz.mvvm

/**
 * An implementation of mechanism to save and restore instance state.
 * That helps to handle system-initiated process death.
 *
 * @see [ShareableState]
 */
actual class SaveableShareableState<T : Any>(
    initialValue: T,
) : ShareableState<T>(initialValue, {})
