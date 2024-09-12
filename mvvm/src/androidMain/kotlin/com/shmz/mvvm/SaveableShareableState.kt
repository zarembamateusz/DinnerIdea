package com.shmz.mvvm

import kotlin.reflect.KProperty

/**
 * An implementation of mechanism to save and restore instance state.
 * That helps to handle system-initiated process death.
 *
 * @see [ShareableState]
 */
actual class SaveableShareableState<T : Any>(
    initialValue: T,
    private val holderKey: String,
    private val saveValue: (fieldKey: String, value: T) -> Unit,
    private val getSavedValue: (fieldKey: String) -> T?
) : ShareableState<T>(initialValue = initialValue) {

    private var usedSavedValue: Boolean = false

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        updateBaseValue(property.name)
        return super.getValue(thisRef, property)
    }

    private fun updateBaseValue(propertyName: String) {
        if (usedSavedValue.not()) {
            getSavedValue(key(propertyName))?.let {
                super.value = it
            }
            usedSavedValue = true
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        saveValue(key(property.name), value)
        super.setValue(thisRef, property, value)
    }

    private fun key(propertyName: String) = "$holderKey:$propertyName"
}
