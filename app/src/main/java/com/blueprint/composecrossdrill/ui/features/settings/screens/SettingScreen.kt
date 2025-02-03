package com.blueprint.composecrossdrill.ui.features.settings.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.ui.theme.spacing
import com.blueprint.composecrossdrill.utils.LanguageManager
import com.blueprint.composecrossdrill.utils.LanguagePreference
import com.blueprint.composecrossdrill.utils.getLanguagePreference
import com.blueprint.composecrossdrill.utils.updateLocale

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun SettingsScreen(
    navController: NavController,
) {
    var shouldShowLanguageDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val languageManager = LanguageManager(context)
    val currentLanguage = getLanguagePreference()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.settings)) }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Menu")
                }
            })
        }
    ) { innerPadding ->
        if (shouldShowLanguageDialog) {
            ShowLanguageChangeDialog(onDismissRequest = {
                shouldShowLanguageDialog = false
            }, onLanguageSelected = { selectedLan ->
                shouldShowLanguageDialog = false
                languageManager.setLanguage(selectedLan)
            })
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        shouldShowLanguageDialog = true
                    }
                    .padding(
                        start = MaterialTheme.spacing.medium,
                        end = MaterialTheme.spacing.medium
                    )
                    .clip(
                        shape = MaterialTheme.shapes.medium
                    )
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.change_language))
                Text(text = stringResource(R.string.tap_here_to_change_language))
            }
        }
    }
}