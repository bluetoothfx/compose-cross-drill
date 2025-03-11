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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.navigation.NavRoute
import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.DashboardViewModel
import com.blueprint.composecrossdrill.ui.theme.spacing
import com.blueprint.composecrossdrill.utils.components.network.ResultWrapper
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = koinViewModel(),
    navController: NavController
) {
    val recipesResult by dashboardViewModel.recipe.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Fetch recipes if empty (only when the screen is first created)
    LaunchedEffect(Lifecycle.State.CREATED) {
        if (recipesResult is ResultWrapper.Loading || recipesResult is ResultWrapper.Failure) {
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
                        stringResource(R.string.app_name),
                        modifier = Modifier.padding(MaterialTheme.spacing.medium),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider()

                    Text(
                        stringResource(R.string.recipes),
                        modifier = Modifier.padding(MaterialTheme.spacing.medium),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text(stringResource(R.string.sample_recipe)) },
                        selected = false,
                        onClick = {
                            navController.navigate(NavRoute.SIMPLE_RECIPE_HOME.name)
                        }
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = MaterialTheme.spacing.small))

                    Text(
                        stringResource(R.string.contact_and_support),
                        modifier = Modifier.padding(MaterialTheme.spacing.medium),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text(stringResource(R.string.settings)) },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                        badge = { Text("20") }, // Placeholder
                        onClick = {
                            navController.navigate(NavRoute.SETTINGS.name)
                        }
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(stringResource(R.string.home)) }, navigationIcon = {
                    IconButton(onClick = {
                        scope.launch { drawerState.open() }
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                })
            }
        ) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                when (val result = recipesResult) {
                    is ResultWrapper.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is ResultWrapper.Success -> {
                        LazyColumn {
                            items(result.data.size) { pos ->
                                RecipeCard(result.data[pos], onItemClick = { recipe ->
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        "recipe",
                                        recipe
                                    )
                                    navController.navigate(NavRoute.DETAILS.name)
                                })
                            }
                        }
                    }

                    is ResultWrapper.Failure -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Error: ${result.message}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}

