package com.blueprint.composecrossdrill.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.ui.features.dashboard.screens.DashboardScreen
import com.blueprint.composecrossdrill.ui.features.dashboard.simplerecipe.SimpleRecipeHome
import com.blueprint.composecrossdrill.ui.features.details.screens.DetailsScreen
import com.blueprint.composecrossdrill.ui.features.settings.SettingsScreen
import com.blueprint.composecrossdrill.utils.ScaleTransitionDirection
import com.blueprint.composecrossdrill.utils.scaleIntoContainer
import com.blueprint.composecrossdrill.utils.scaleOutOfContainer

enum class NavRoute {
    HOME,
    DETAILS,
    SIMPLE_RECIPE_HOME,
    SETTINGS
}

@Composable
fun MainNavigation() {
    val mainNavController = rememberNavController()

    NavHost(mainNavController, startDestination = NavRoute.HOME.name) {
        composable(
            route = NavRoute.HOME.name,
        ) {
            DashboardScreen(navController = mainNavController)
        }
        composable(
            route = NavRoute.SIMPLE_RECIPE_HOME.name,
        ) {
            SimpleRecipeHome(navController = mainNavController)
        }
        composable(
            route = NavRoute.DETAILS.name,
            enterTransition = {
                scaleIntoContainer()
            },
            exitTransition = {
                scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
            },
        ) {
            val recipe =
                mainNavController.previousBackStackEntry?.savedStateHandle?.get<Recipe>("recipe")

            if (recipe != null) {
                DetailsScreen(navController = mainNavController, recipe = recipe)
            }
        }
        composable(route = NavRoute.SETTINGS.name) {
            SettingsScreen(navController = mainNavController)
        }
    }
}