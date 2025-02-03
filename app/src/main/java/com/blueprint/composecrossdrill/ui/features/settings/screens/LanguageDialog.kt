package com.blueprint.composecrossdrill.ui.features.settings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.domain.model.language.LanguageItem
import com.blueprint.composecrossdrill.ui.theme.spacing


@Composable
fun ShowLanguageChangeDialog(
    onDismissRequest: () -> Unit,
    onLanguageSelected: (selectedLan: String) -> Unit
) {
    Dialog(onDismissRequest = {
        onDismissRequest()
    }) {
        val languages: MutableList<LanguageItem> = mutableListOf()
        languages.add(LanguageItem("en", "English", R.drawable.ic_english))
        languages.add(LanguageItem("bn", "Bengali", R.drawable.ic_bangla))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(MaterialTheme.spacing.small)
        ) {
            items(languages.size) { pos ->
                LanguageCard(languages[pos], onLanguageSelected = { selectedLan ->
                    onLanguageSelected(selectedLan)
                })
                if (pos < languages.lastIndex)
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onBackground,
                        thickness = .3.dp
                    )
            }
        }
    }
}