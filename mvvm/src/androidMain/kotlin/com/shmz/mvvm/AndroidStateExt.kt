package com.shmz.mvvm

import androidx.lifecycle.SavedStateHandle.Companion.validateValue

/**
 * Create a [ShareableState] to observe state changes.
 *
 * Requires that the receiver implements [CanPersistState], otherwise throws [IllegalStateException].
 * @throws IllegalStateException when receiver is not a [CanPersistState] or [validateValue] returns false.
 */
actual fun <T : Any> StateHolder.saveableState(initialValue: T): ShareableState<T> {
    require(this is CanPersistState) {
        "Cannot save state from something which does not conform to `CanPersistState` interface"
    }
    return if (validateValue(initialValue)) {
        SaveableShareableState(
            initialValue = initialValue,
            holderKey = key(),
            getSavedValue = { key -> savedStateHandle[key] },
            saveValue = { key, value -> savedStateHandle[key] = value }
        )
    } else {
        throw IllegalArgumentException(
            "Cannot persist state of value which cannot be store in Parcel"
        )
    }
}

private fun StateHolder.key() = this::class.java.simpleName
