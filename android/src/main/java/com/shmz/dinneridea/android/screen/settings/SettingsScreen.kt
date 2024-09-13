package com.shmz.dinneridea.android.screen.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shmz.dinneridea.android.screen.StartEffect
import com.shmz.dinneridea.android.screen.components.ErrorScreen
import com.shmz.dinneridea.android.screen.components.ListItemSecondaryText
import com.shmz.dinneridea.android.screen.components.LoadingScreen
import com.shmz.dinneridea.android.screen.components.TitleAppBar
import com.shmz.dinneridea.asInt
import com.shmz.dinneridea.screens.settings.SettingsScreenState
import com.shmz.dinneridea.screens.settings.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel {
        SettingsViewModel()
    }
) {
    StartEffect {
        viewModel.onStart()
    }
    SettingsScreen(
        screenState = viewModel.screenState,
        changeDisplayedMealsCount = viewModel::changeDisplayedMealsCount
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsScreen(
    screenState: SettingsScreenState,
    changeDisplayedMealsCount: (Int) -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TitleAppBar(title = "Insight")
        },
        content = {
            val focusManager = LocalFocusManager.current
            val keyboardController = LocalSoftwareKeyboardController.current
            Box(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding(), bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                when (screenState) {
                    is SettingsScreenState.Error -> ErrorScreen(
                        errorMessage = screenState.errorMessage,
                        onRetry = screenState.onRetry
                    )

                    is SettingsScreenState.Idle -> SettingsPage(
                        displayedMealsCount = screenState.displayedMealsCount,
                        changeDisplayedMealsCount = changeDisplayedMealsCount,
                        onClearFocus = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }
                    )

                    SettingsScreenState.Loading -> LoadingScreen()
                }
            }
        }
    )
}

@Composable
private fun SettingsPage(
    displayedMealsCount: Int,
    changeDisplayedMealsCount: (Int) -> Unit,
    onClearFocus: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var errorMessage by remember { mutableStateOf("") }
        var mealsCount by remember { mutableStateOf(TextFieldValue(displayedMealsCount.toString())) }
        ListItemSecondaryText("How many meal ideas do you want to display?")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = mealsCount,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                mealsCount = it
            },
            colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colorScheme.onPrimary )
        )
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary),
            onClick = {
                mealsCount.text.asInt()?.let {
                    changeDisplayedMealsCount(it)
                    onClearFocus()
                } ?: run {
                    errorMessage = "Invalid meal ideas count"
                }
            }
        ) {
            Text(
                text = "Save",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        if (errorMessage.isNotEmpty()) {
            ListItemSecondaryText(text = errorMessage)
        }
    }
}