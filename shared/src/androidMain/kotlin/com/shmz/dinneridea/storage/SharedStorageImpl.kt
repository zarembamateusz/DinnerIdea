package com.shmz.dinneridea.storage

import android.content.Context
import android.content.SharedPreferences
import com.shmz.dinneridea.AndroidPlatform

actual class SharedStorageImpl actual constructor(name: String?) : SharedStorage {
    private val sharedPrefs = with(AndroidPlatform.context) {
        getSharedPreferences(name ?: packageName, Context.MODE_PRIVATE)
    }

    override fun getLong(key: String): Long? =
        if (sharedPrefs.contains(key)) sharedPrefs.getLong(key, 0L) else null

    override fun setLong(key: String, value: Long) {
        sharedPrefs.modify { putLong(key, value) }
    }

    override fun remove(vararg keys: String) {
        sharedPrefs.edit().apply {
            keys.forEach(::remove)
            apply()
        }
    }

    override fun clear() {
        sharedPrefs
            .edit()
            .clear()
            .apply()
    }
}

private inline fun SharedPreferences.modify(modifier: SharedPreferences.Editor.() -> Unit) {
    edit().apply {
        modifier()
        apply()
    }
}
