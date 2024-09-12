package com.shmz.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/**
 * An abstract class of shared view model on the iOS platform.
 *
 * Suppression: An abstract class without a concrete member can be refactored to an interface
 * but interface can't inherit from classes.
 */
@Suppress("UnnecessaryAbstractClass")
actual abstract class ViewModel actual constructor() {

    actual val viewModelScope: CoroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main.immediate
    )

    actual open fun onClear() {
        viewModelScope.cancel()
    }
}
