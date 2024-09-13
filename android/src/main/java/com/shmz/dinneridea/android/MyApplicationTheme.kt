package com.shmz.dinneridea.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    androidx.compose.material3.MaterialTheme(
        colorScheme = colors
        ,
        content = content
    )
}
