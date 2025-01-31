package com.blueprint.composecrossdrill.ui.features.details.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@Composable
fun DetailsScreen(navController: NavController, recipe: Recipe) {
    val coroutineScope = rememberCoroutineScope()

    // State to track the visibility of the bottom sheet, preserved across configuration changes
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    // SheetState to control the bottom sheet's state
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var shouldShowDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Details") }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Menu")
                }
            })
        },
        bottomBar = {
            RecipeBottomActions(
                onAddRecipeClick = {
                    showBottomSheet = true
                },
                onFavouriteClick = {})
        }
    ) { innerPadding ->
        //Bottom Sheet
        if (showBottomSheet) {
            AddRecipeBottomSheet(sheetState, onDismissRequest = {
                coroutineScope.launch { showBottomSheet = false }
            }, onRecipeSubmitRequest = {
                showBottomSheet = false
                shouldShowDialog = true
            })
        }

        //Recipe Submission Success Dialog
        if (shouldShowDialog) {
            ShowRecipeSubmissionSuccessDialog(onDismissRequest = {
                shouldShowDialog = false
            })
        }

        //Main Content
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = recipe.image,
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .align(Alignment.CenterHorizontally)
                    .height(180.dp)
                    .clip(shape = MaterialTheme.shapes.medium),
                placeholder = painterResource(R.drawable.ic_loading),
                error = painterResource(R.drawable.ic_error),
                contentScale = ContentScale.Crop,
                onSuccess = { /* Handle success */ },
                onLoading = { /* Show loading spinner */ },
            )
            Text(
                text = recipe.name.orEmpty(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFD700) // Gold color
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${recipe.rating} (${recipe.reviewCount} reviews)",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                Text(
                    text = "Prep Time: ${recipe.prepTimeMinutes} mins",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "Cook Time: ${recipe.cookTimeMinutes} mins",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                fontWeight = FontWeight.Bold
            )
            Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
                recipe.ingredients.forEach { ingredient ->
                    Text(
                        text = "• $ingredient",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Text(
                text = "Instructions",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                fontWeight = FontWeight.Bold
            )
            Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
                recipe.instructions.forEachIndexed { index, instruction ->
                    Text(
                        text = "${index + 1}. $instruction",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Text(
                text = "Tags",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                fontWeight = FontWeight.Bold
            )
            FlowRow(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth(),
            ) {
                recipe.tags.forEach { tag ->
                    AssistChip(
                        label = { Text(tag) },
                        onClick = {},
                        modifier = Modifier.padding(end = MaterialTheme.spacing.small)
                    )
                }
            }
        }

    }
}

@Composable
fun ShowRecipeSubmissionSuccessDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = {
        onDismissRequest()
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(32.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.onPrimary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Submitted",
                modifier = Modifier.size(width = 48.dp, height = 48.dp)
            )
            Text(
                text = "Your submission has been received.",
                modifier = Modifier
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}