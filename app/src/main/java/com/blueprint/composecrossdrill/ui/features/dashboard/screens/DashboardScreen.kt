package com.blueprint.composecrossdrill.ui.features.dashboard.screens

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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.data.repository.DashboardRepositoryImpl
import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.DashboardViewModel
import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.MyViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = viewModel(
        factory = MyViewModelFactory(
            DashboardRepositoryImpl()
        )
    )
) {
    val context = LocalContext.current
    val users by dashboardViewModel.users

    LaunchedEffect(Lifecycle.State.STARTED) {
        val inputStream = context.resources.openRawResource(R.raw.data)
        dashboardViewModel.getUsers(inputStream)
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
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(users.size) { pos ->
                UserCard(users[pos])
            }
        }
    }
}