package com.shmz.dinneridea.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFE02B20),
            onPrimary = Color(0xFFFFFFFF),
            onSecondary = Color(0xFFCAC9C9),
            secondary = Color(0xFFA76460),
            background = Color(0xFF222222)
        )
    } else {
        lightColorScheme(
            primary = Color(0xFFE02B20),
            onPrimary = Color(0xFFFFFFFF),
            onSecondary = Color(0xFFCAC9C9),
            secondary = Color(0xFFA76460),
            background = Color(0xFF222222)
        )
    }
    androidx.compose.material3.MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
