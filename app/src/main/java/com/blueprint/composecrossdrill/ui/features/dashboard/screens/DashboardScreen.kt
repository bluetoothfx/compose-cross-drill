package com.blueprint.composecrossdrill.ui.features.dashboard.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blueprint.composecrossdrill.R
import com.blueprint.composecrossdrill.data.repository.DashboardRepositoryImpl
import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.DashboardViewModel
import com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel.MyViewModelFactory
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
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
        Column {
            GlideImage(
                model = "https://fastly.picsum.photos/id/26/4209/2769.jpg?hmac=vcInmowFvPCyKGtV7Vfh7zWcA_Z0kStrPDW3ppP0iGI",
                contentDescription = "Glide Sample",
                loading = placeholder(ColorPainter(Color.Red)),
                modifier = Modifier
                    .padding(innerPadding)
                    .clickable(onClick = {})
                    .align(Alignment.CenterHorizontally)
                    .height(200.dp),
                contentScale = ContentScale.FillWidth,
            )

            LazyColumn {
                items(users.size) { pos ->
                    UserCard(users[pos])
                }
            }
        }
    }
}