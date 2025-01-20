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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.blueprint.composecrossdrill.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onRecipeSubmitRequest: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var instructions by remember { mutableStateOf("") }
    var cookTime by remember { mutableStateOf("") }
    var prepTime by remember { mutableStateOf("") }
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
                .padding(MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Recipe Title") },
                placeholder = { Text("Enter your recipe name") },
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
                label = { Text("Recipe Instruction") },
                placeholder = { Text("Enter Instructions Here") },
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
                    label = { Text("Cook Time") },
                    placeholder = { Text("Enter cook time") },
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
                    label = { Text("Prep Time") },
                    placeholder = { Text("Enter prep time") },
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
                    onRecipeSubmitRequest()
                }
            ) {
                Text("Submit Recipe")
            }
        }
    }
}