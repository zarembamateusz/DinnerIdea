package com.shmz.dinneridea.android

import android.app.Application
import com.shmz.dinneridea.AndroidPlatform

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidPlatform.context = this
    }
}