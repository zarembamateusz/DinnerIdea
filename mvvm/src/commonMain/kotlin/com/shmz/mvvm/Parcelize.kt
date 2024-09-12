package com.shmz.mvvm

/**
 * The annotation is applicable only to classes that implements [Parcelable] (directly or indirectly).
 */
@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class Parcelize()
