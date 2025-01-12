package com.blueprint.composecrossdrill.ui.features.dashboard.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.blueprint.composecrossdrill.navigation.NavRoute
import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = koinViewModel(),
    navController: NavController
) {
    val recipes by dashboardViewModel.recipe
    val isLoading by dashboardViewModel.isLoading.collectAsState()

    LaunchedEffect(Lifecycle.State.STARTED) {
        dashboardViewModel.getRecipes()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home") }, navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            })
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            if (isLoading){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            else {
                LazyColumn {
                    items(recipes.size) { pos ->
                        RecipeCard(recipes[pos], onItemClick = { recipe ->
                            navController.currentBackStackEntry?.savedStateHandle?.set("recipe", recipe)
                            navController.navigate(NavRoute.DETAILS.name)
                        })
                    }
                }
            }
        }
    }
}