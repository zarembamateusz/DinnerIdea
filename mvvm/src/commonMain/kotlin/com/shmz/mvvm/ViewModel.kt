package com.shmz.mvvm

import kotlinx.coroutines.CoroutineScope

/**
 * Abstract representation of shared view model.
 *
 * Suppression: An abstract class without a concrete member can be refactored to an interface
 * but interface can't inherit from classes.
 */
@Suppress("UnnecessaryAbstractClass")
expect abstract class ViewModel constructor() {

    /**
     * CoroutineScope tied to this ViewModel. This scope will be canceled when [onClear] will be called,
     * This scope is bound to Dispatchers.Main.immediate
     */
    val viewModelScope: CoroutineScope

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     * It is useful when ViewModel observes some data and you need to clear this subscription
     * to prevent a leak of this ViewModel.
     */
    open fun onClear()
}
