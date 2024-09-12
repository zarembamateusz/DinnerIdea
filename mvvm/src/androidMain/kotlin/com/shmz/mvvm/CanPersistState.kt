package com.shmz.mvvm

import androidx.lifecycle.SavedStateHandle

/**
 * Marker interface to indicate that the inheritor supports state saving.
 */
interface CanPersistState {

    var savedStateHandle: SavedStateHandle
}
