package com.shmz.mvvm

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

private val androidx.lifecycle.ViewModel.scope
    get() = this.viewModelScope

/**
 * An abstract class of shared view model on the Android platform.
 *
 * @see [androidx.lifecycle.ViewModel]
 */
actual abstract class ViewModel actual constructor() :
    androidx.lifecycle.ViewModel() {

    final override fun onCleared() {
        onClear()
        super.onCleared()
    }

    actual open fun onClear() = Unit

    actual val viewModelScope: CoroutineScope
        get() = scope
}
