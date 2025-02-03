package com.blueprint.composecrossdrill.ui.features.details.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onRecipeSubmitRequest: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    var title by rememberSaveable { mutableStateOf("") }
    var instructions by rememberSaveable { mutableStateOf("") }
    var cookTime by rememberSaveable { mutableStateOf("") }
    var prepTime by rememberSaveable { mutableStateOf("") }
    val cuisines = listOf("Indian", "Thai", "Italian", "Chinese")
    val mealType = listOf("Dinner", "Lunch", "Snack", "Dessert", "Breakfast")

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(R.string.recipe_title)) },
                placeholder = { Text(stringResource(R.string.recipe_title_hint)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.medium),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )

            TextField(
                value = instructions,
                onValueChange = { instructions = it },
                label = { Text(stringResource(R.string.instructions)) },
                placeholder = { Text(stringResource(R.string.instructions_hint)) },
                singleLine = false,
                maxLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = MaterialTheme.shapes.medium),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )

            Row {
                TextField(
                    value = cookTime,
                    onValueChange = { cookTime = it },
                    label = { Text(stringResource(R.string.cook_time)) },
                    placeholder = { Text(stringResource(R.string.cook_time_hint)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.5f)
                        .clip(shape = MaterialTheme.shapes.medium),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                TextField(
                    value = prepTime,
                    onValueChange = { prepTime = it },
                    label = { Text(stringResource(R.string.prep_time)) },
                    placeholder = { Text(stringResource(R.string.prep_time_hint)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.5f)
                        .clip(shape = MaterialTheme.shapes.medium),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                )
            }
            ExtendedDropDown(
                list = cuisines,
                defaultValue = "Select Cuisine",
                onItemSelected = { selectedItem -> })
            ExtendedDropDown(
                list = mealType,
                defaultValue = "Select Meal Type",
                onItemSelected = { selectedItem -> })
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    coroutineScope.launch { sheetState.hide() }
                    onRecipeSubmitRequest()
                }
            ) {
                Text(stringResource(R.string.submit_recipe))
            }
        }
    }
}