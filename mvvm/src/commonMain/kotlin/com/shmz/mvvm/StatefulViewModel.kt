package com.shmz.mvvm

/**
 * Abstract representation of view model that inherits members from [StateHolder].
 *
 * Suppression: An abstract class without a concrete member can be refactored to an interface
 * but interface can't inherit from classes.
 */
@Suppress("UnnecessaryAbstractClass")
abstract class StatefulViewModel : ViewModel(), StateHolder by StateHolder()
