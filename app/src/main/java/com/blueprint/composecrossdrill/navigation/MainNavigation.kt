package com.blueprint.composecrossdrill.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blueprint.composecrossdrill.ui.features.dashboard.screens.DashboardScreen
import com.blueprint.composecrossdrill.ui.features.details.screens.DetailsScreen

enum class NavRoute{
    HOME,
    DETAILS
}

@Composable
fun MainNavigation() {
    val mainNavController = rememberNavController()

    NavHost(mainNavController, startDestination = NavRoute.HOME.name) {
        composable(NavRoute.HOME.name){
            DashboardScreen()
        }
        composable(NavRoute.DETAILS.name){
            DetailsScreen()
        }
    }
}