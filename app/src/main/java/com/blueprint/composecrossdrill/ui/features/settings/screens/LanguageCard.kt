package com.blueprint.composecrossdrill.ui.features.settings.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.domain.model.language.LanguageItem
import com.blueprint.composecrossdrill.ui.theme.spacing

@Composable
fun LanguageCard(languageItem: LanguageItem, onLanguageSelected: (shortForm: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
            .clickable {
                onLanguageSelected(languageItem.shortForm)
            },
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
            Text(
                text = stringResource(
                    R.string.choose_x_as_default_language,
                    languageItem.fullForm
                )
            )
        }
    }
}