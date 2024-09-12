package com.shmz.mvvm

/**
 * The property annotated with IgnoredOnParcel will not be stored into a Parcel.
 *
 * On the iOS platform, there is no need to use it.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
expect annotation class IgnoreOnParcel()
