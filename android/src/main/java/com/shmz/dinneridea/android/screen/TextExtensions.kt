package com.shmz.dinneridea.android.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Int.asString(vararg formatArgs: Any): String =
    if(formatArgs.isEmpty()) {
        LocalContext.current.resources.getString(this)
    } else {
        LocalContext.current.resources.getString(this, *formatArgs)
    }