package com.blueprint.composecrossdrill.ui.features.details.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.blueprint.composecrossdrill.ui.theme.spacing

@Composable
fun RecipeBottomActions(
    onAddRecipeClick: () -> Unit,
    onFavouriteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 1.dp)
            .padding(
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.medium,
                bottom = 32.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onAddRecipeClick,
            modifier = Modifier
                .weight(.8f)
                .fillMaxWidth(.8f)
                .height(48.dp)
        ) {
            Text("Add Recipe")
        }
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        IconButton(
            onClick = onFavouriteClick,
            modifier = Modifier
                .weight(.2f)
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.primary),
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Favorite",
                tint = MaterialTheme.colorScheme.surface
            )
        }
    }
}