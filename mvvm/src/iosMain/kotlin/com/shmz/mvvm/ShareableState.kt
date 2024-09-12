package com.shmz.mvvm

import kotlin.reflect.KProperty

/**
 * Actual implementation of [ShareableState] for iOS platform.
 *
 * @property[initialValue] initial value that is wrapped by [ShareableState]
 * @property[objectWillChange] callback that is invoked after every write to the [initialValue] property to signal that
 * the values has changed.
 */
actual open class ShareableState<T : Any>(
    private var initialValue: T,
    private val objectWillChange: () -> Unit
) {

    actual open operator fun getValue(thisRef: Any?, property: KProperty<*>): T = initialValue

    actual open operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        initialValue = value
        objectWillChange()
    }
}

/**
 * Creates [ShareableState] with provided initial value and callback set to one defined
 * in [StateHolder]'s [Binder] object.
 *
 * @param[initialValue] initial value that will be wrapped by [ShareableState]
 * @return[ShareableState] wrapping [initialValue]
 */
actual fun <T : Any> StateHolder.state(initialValue: T): ShareableState<T> =
    ShareableState(initialValue) { binder.objectWillChange() }
