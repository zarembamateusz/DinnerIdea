package com.shmz.dinneridea.android.screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ListItemSecondaryText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSecondary,
    style: TextStyle = MaterialTheme.typography.titleSmall,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color,
        overflow = overflow,
        textAlign = textAlign,
        fontWeight = FontWeight.Normal,
        maxLines = maxLines
    )
}