package com.shmz.mvvm

/**
 * Actual implementation of [Binder].
 *
 * @property[objectWillChange] should be set to
 * [ObservableObject](https://developer.apple.com/documentation/combine/observableobject)
 * [objectWillChange](https://developer.apple.com/documentation/combine/observableobject/objectwillchange-5gopl)
 * property from iOS side, available in context of
 * [ObservableObject](https://developer.apple.com/documentation/combine/observableobject) protocol
 */
actual open class Binder {

    /**
     * On write operation the value is invoked to deliver updates of all stored [ShareableState] properties
     * withing a context of [StateHolder] the [Binder] is associated with.
     */
    open var objectWillChange: () -> Unit = {}
}
