package com.blueprint.composecrossdrill.ui.features.dashboard.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.domain.model.receipies.Recipes
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalLayoutApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun RecipeCard(recipes: Recipes, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onItemClick.invoke()
        }
    ) {
        Column {
            GlideImage(
                model = recipes.image,
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = {})
                    .align(Alignment.CenterHorizontally)
                    .height(150.dp)
                    .clip(shape = RoundedCornerShape(16.dp)),
                loading = placeholder(R.drawable.ic_loading),
                failure = placeholder(R.drawable.ic_error),
                contentScale = ContentScale.Crop,
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = recipes.name ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD700) // Gold color
                    )
                    Text(
                        text = "${recipes.rating} (${recipes.reviewCount} reviews)",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Prep: ${recipes.prepTimeMinutes} mins | Cook: ${recipes.cookTimeMinutes} mins",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Servings: ${recipes.servings}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                FlowRow {
                    recipes.tags.forEach { tag ->
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