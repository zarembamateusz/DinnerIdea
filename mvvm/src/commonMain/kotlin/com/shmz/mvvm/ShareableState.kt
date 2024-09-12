@file:JvmName("ShareableStateExt")

package com.shmz.mvvm

import kotlin.jvm.JvmName
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * State<T : Any> is a [property delegate](https://kotlinlang.org/docs/delegated-properties.html) that
 * represents a wrapper of type T which provides platform specific implementation allowing to receive update on target
 * platforms that happen during write operations from KMM shared module.
 */
expect open class ShareableState<T : Any> {

    open operator fun getValue(thisRef: Any?, property: KProperty<*>): T

    open operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
}

/**
 * Returns [ShareableState] of type T.
 *
 * This function can be used with `by` syntax.
 *
 * ```
 * var state : String by state("Example")
 * ```
 * @param initialValue initial value of type T
 * @receiver [StateHolder]
 * @return [ShareableState] of type T
 */
expect fun <T : Any> StateHolder.state(
    initialValue: T
): ShareableState<T>

/**
 * Returns a property delegate for a [ShareableState] of value [T]
 * that calls a specified callback function when changed.
 *
 * This function can be used with `by` syntax
 *
 * ```
 * var state : String by observableState("Example") { oldValue, newValue ->
 *   logger.info("newValue: $newValue, oldValue: $oldValue")
 * }
 * ```
 * To update param created by `observableState` simply write new value to it.
 * This will lead to changes on platform side.
 * Note that write operation to a field is not synchronized and synchronization is on user side.
 * ```
 * var state : String by observableState("Example") { oldValue, newValue ->
 *   logger.info("newValue: $newValue, oldValue: $oldValue")
 * }
 *
 * fun update() {
 *     observableState = "Other example" // newValue: Other example, oldValue: Example
 * }
 * ```
 *
 * @param initialValue initial value of type T
 * @param onChange the callback which is called after the change of the property is made.
 * The value of the property has already been changed when this callback is invoked.
 * @receiver [StateHolder]
 * @return [ShareableState] of type T
 */
inline fun <T : Any> StateHolder.observableState(
    initialValue: T,
    crossinline onChange: (oldValue: T, newValue: T) -> Unit,
): ReadWriteProperty<StateHolder, T> = object : ObservableState<T>(this, initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = onChange(oldValue, newValue)
}
