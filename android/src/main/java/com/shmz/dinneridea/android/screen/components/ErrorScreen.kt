package com.shmz.dinneridea.android.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shmz.dinneridea.android.R

@Composable
fun ErrorScreen(
    errorMessage: String = "",
    onRetry: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_error),
            contentScale = ContentScale.None,
            alignment = Alignment.Center,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(height = 40.dp))
        ListItemText(text = errorMessage, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(height = 20.dp))
        if (onRetry != null) {
            Button(
                modifier = Modifier.padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary),
                onClick = onRetry
            ) {
                Text(
                    text = "Try again",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}