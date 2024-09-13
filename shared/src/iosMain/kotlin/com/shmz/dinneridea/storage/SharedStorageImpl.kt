package com.shmz.dinneridea.storage

import platform.Foundation.NSUserDefaults

actual class SharedStorageImpl actual constructor(name: String?) : SharedStorage {
    private val userDefaults: NSUserDefaults = if (name == null) {
        NSUserDefaults.standardUserDefaults
    } else {
        NSUserDefaults(suiteName = name)
    }

    override fun getLong(key: String): Long? =
        userDefaults.objectForKey(key) as? Long

    override fun setLong(key: String, value: Long) {
        userDefaults.setInteger(value, key)
    }

    override fun remove(vararg keys: String) {
        keys.forEach {
            userDefaults.removeObjectForKey(it)
        }
    }

    override fun clear() {
        val allKeys = userDefaults.dictionaryRepresentation().keys
        allKeys
            .mapNotNull { it?.toString() }
            .forEach(userDefaults::removeObjectForKey)
    }
}