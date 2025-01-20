package com.blueprint.composecrossdrill.ui.features.dashboard.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.blueprint.composecrossdrill.navigation.NavRoute
import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.DashboardViewModel
import com.blueprint.composecrossdrill.ui.theme.spacing
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = koinViewModel(),
    navController: NavController
) {
    val recipes by dashboardViewModel.recipe.collectAsState()
    val isLoading by dashboardViewModel.isLoading.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    LaunchedEffect(Lifecycle.State.CREATED) {
        if (recipes.isEmpty()) {
            dashboardViewModel.getRecipes()
        }
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Compose Cross Drill",
                        modifier = Modifier.padding(MaterialTheme.spacing.medium),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider()

                    Text(
                        "Sample Menu",
                        modifier = Modifier.padding(MaterialTheme.spacing.medium),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text("Simple Recipe") },
                        selected = false,
                        onClick = {
                            navController.navigate(NavRoute.SIMPLE_RECIPE_HOME.name)
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Item 2") },
                        selected = false,
                        onClick = { /* Handle click */ }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = MaterialTheme.spacing.small))

                    Text(
                        "Contact & Support",
                        modifier = Modifier.padding(MaterialTheme.spacing.medium),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text("Settings") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                        badge = { Text("20") }, // Placeholder
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Contact Us") },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.Outlined.MailOutline,
                                contentDescription = null
                            )
                        },
                        onClick = { },
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Home") }, navigationIcon = {
                    IconButton(onClick = {
                        scope.launch { drawerState.open() }
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                })
            }
        ) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                if (isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn {
                        items(recipes.size) { pos ->
                            RecipeCard(recipes[pos], onItemClick = { recipe ->
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "recipe",
                                    recipe
                                )
                                navController.navigate(NavRoute.DETAILS.name)
                            })
                        }
                    }
                }
            }
        }
    }
}