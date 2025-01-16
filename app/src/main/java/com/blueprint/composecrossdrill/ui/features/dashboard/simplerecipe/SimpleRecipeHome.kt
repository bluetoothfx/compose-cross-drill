package com.blueprint.composecrossdrill.ui.features.dashboard.simplerecipe

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleRecipeHome(
    navController: NavController
) {
    val dashboardViewModel = koinViewModel<DashboardViewModel>()

    Text(
        "Contact & Support",
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.titleMedium
    )

}