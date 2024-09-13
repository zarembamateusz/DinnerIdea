package com.shmz.dinneridea

import android.content.Context

object AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    lateinit var context: Context
}

actual fun getPlatform(): Platform = AndroidPlatform