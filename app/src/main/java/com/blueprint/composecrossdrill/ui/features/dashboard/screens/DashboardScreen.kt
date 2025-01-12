package com.blueprint.composecrossdrill.ui.features.dashboard.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = koinViewModel()
) {
    val recipes by dashboardViewModel.recipes

    LaunchedEffect(Lifecycle.State.STARTED) {
        dashboardViewModel.getRecipes()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dashboard") }, navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            })
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            LazyColumn {
                items(recipes.size) { pos ->
                    RecipeCard(recipes[pos])
                }
            }
        }
    }
}