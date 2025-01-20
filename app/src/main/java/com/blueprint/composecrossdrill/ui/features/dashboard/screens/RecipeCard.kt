package com.blueprint.composecrossdrill.ui.features.dashboard.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.domain.model.recipes.Recipe

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeCard(recipe: Recipe, onItemClick: (recipe: Recipe) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .clickable { },
        shape = MaterialTheme.shapes.medium,
        onClick = {
            onItemClick(recipe)
        }
    ) {
        Column {
            AsyncImage(
                model = recipe.image,
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(150.dp)
                    .clip(shape = MaterialTheme.shapes.medium),
                placeholder = painterResource(R.drawable.ic_loading),
                error = painterResource(R.drawable.ic_error),
                contentScale = ContentScale.Crop,
                onSuccess = { /* Handle success */ },
                onLoading = { /* Show loading spinner */ },
            )

            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                Text(
                    text = recipe.name ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD700) // Gold color
                    )
                    Text(
                        text = "${recipe.rating} (${recipe.reviewCount} reviews)",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Text(
                    text = "Prep: ${recipe.prepTimeMinutes} mins | Cook: ${recipe.cookTimeMinutes} mins",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Servings: ${recipe.servings}",
                    style = MaterialTheme.typography.bodyMedium
                )

                FlowRow {
                    recipe.tags.forEach { tag ->
                        AssistChip(
                            label = { Text(tag) },
                            onClick = {},
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
            }
        }
    }
}