package com.blueprint.composecrossdrill.ui.features.details.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun ExtendedDropDown(
    list: List<String>,
    defaultValue: String,
    onItemSelected: (selectedItem: String) -> Unit
) {
    var mExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(defaultValue) }
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    TextField(
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
        value = selectedText,
        onValueChange = { selectedText = it },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                mExpanded = !mExpanded
            }
            .clip(shape = MaterialTheme.shapes.medium),
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
        list.forEach { label ->
            DropdownMenuItem(onClick = {
                onItemSelected(selectedText)
                selectedText = label
                mExpanded = false
            }, text = { Text(text = label) })
        }
    }
}