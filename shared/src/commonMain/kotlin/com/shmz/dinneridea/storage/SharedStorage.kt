package com.shmz.dinneridea.storage

interface SharedStorage {

    fun getLong(key: String): Long?

    fun setLong(key: String, value: Long)

    fun remove(vararg keys: String)

    fun clear()

}

expect class SharedStorageImpl(name: String? = null) : SharedStorage