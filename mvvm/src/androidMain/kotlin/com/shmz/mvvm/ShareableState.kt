package com.shmz.mvvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.reflect.KProperty

/**
 * Actual implementation of [ShareableState] on Android platform.
 *
 * @param[initialValue] param that is going to be initial value of [value] `mutableState`.
 */
actual open class ShareableState<T : Any>(
    initialValue: T,
) {

    /**
     * Holds the state that is observed withing `@Composable` functions on Android platform when the target platform
     * accesses the [ShareableState] wrapper. It's value changes when write operation is performed to [ShareableState]
     * and then it's value is reflected on target platform after `Snapshot` is applied.
     */
    protected var value by mutableStateOf(initialValue)

    actual open operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    actual open operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }
}

/**
 * Creates a [ShareableState].
 *
 * @param[initialValue] initial value that will be wrapped by [ShareableState].
 * @return[ShareableState] wrapping [initialValue].
 */
actual fun <T : Any> StateHolder.state(initialValue: T): ShareableState<T> =
    ShareableState(initialValue)
