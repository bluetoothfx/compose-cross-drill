package com.blueprint.composecrossdrill.ui.features.details.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeBottomSheet(sheetState: SheetState, onDismissRequest: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var cookTime by remember { mutableStateOf("") }
    var prepTime by remember { mutableStateOf("") }
    var mExpanded by remember { mutableStateOf(false) }

    val cuisines = listOf("Indian", "Thai", "Italian", "Chinese")
    var mSelectedText by remember { mutableStateOf("") }
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val context = LocalContext.current

    var photoUri: Uri? by remember { mutableStateOf(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            photoUri = uri
        }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Recipe Title") },
                placeholder = { Text("Enter your recipe name") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp)),
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
                        .clip(shape = RoundedCornerShape(16.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                )
                Spacer(modifier = Modifier.width(8.dp))
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
                        .clip(shape = RoundedCornerShape(16.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                )
            }
            TextField(
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        mExpanded = !mExpanded
                    }
                    .clip(shape = RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                enabled = false,
                trailingIcon = {
                    Image(icon, "contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                }
            )
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                cuisines.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label
                        mExpanded = false
                    }, text = { Text(text = label) })
                }
            }
            Button(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    launcher.launch(
                        PickVisualMediaRequest(
                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            ) {
                Text("Attach Recipe Image")
            }
            if (photoUri != null) {
                val painter = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(context)
                        .data(data = photoUri)
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .height(100.dp),
                )
            }
        }
    }
}