package com.shmz.mvvm

/**
 * Interface for classes whose instances can be written to and restored from a Parcel.
 *
 * Parcel class is designed as a high-performance IPC transport that
 * helps read and write data of various types on the Android Platform.
 *
 * On the iOS platform, there is no need to use it.
 */
expect interface Parcelable
