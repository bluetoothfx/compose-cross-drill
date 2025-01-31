package com.blueprint.composecrossdrill.ui.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.domain.model.language.LanguageItem
import com.blueprint.composecrossdrill.ui.theme.spacing

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun SettingsScreen(navController: NavController) {
    var shouldShowLanguageDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") }, navigationIcon = {
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
                Text(text = "Change Language")
                Text(text = "Tap Here to Change Language")
            }
        }
    }
}

@Composable
fun ShowLanguageChangeDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = {
        onDismissRequest()
    }) {
        val languages: MutableList<LanguageItem> = mutableListOf()
        languages.add(LanguageItem("en", "English", R.drawable.ic_english))
        languages.add(LanguageItem("Bn", "Bengali", R.drawable.ic_bangla))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(MaterialTheme.spacing.small)
        ) {
            items(languages.size) { pos ->
                LanguageCard(languages[pos])
            }
        }
    }
}

@Composable
fun LanguageCard(languageItem: LanguageItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Left
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(languageItem.imageResId),
            contentDescription = "Language Icon",
            modifier = Modifier.padding(end = 8.dp)
        )

        Column {
            Text(text = languageItem.fullForm)
            Text(text = "Choose ${languageItem.fullForm} as default language")
        }
    }
}