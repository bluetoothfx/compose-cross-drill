package com.blueprint.composecrossdrill.ui.features.dashboard.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
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
    var photoUri: Uri? by remember { mutableStateOf(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            photoUri = uri
        }

    LaunchedEffect(Lifecycle.State.STARTED) {
        val inputStream = context.resources.openRawResource(R.raw.data)
        dashboardViewModel.getUsers(inputStream)
        dashboardViewModel.getUserTodos()
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
                modifier = Modifier
                    .padding(innerPadding)
                    .clickable(onClick = {})
                    .align(Alignment.CenterHorizontally)
                    .height(100.dp),
                loading = placeholder(R.drawable.ic_loading),
                failure = placeholder(R.drawable.ic_error),
                contentScale = ContentScale.FillWidth,
            )
            AsyncImage(
                model = "https://fastly.picsum.photos/id/12/2500/1667.jpg?hmac=Pe3284luVre9ZqNzv1jMFpLihFI6lwq7TPgMSsNXw2w",
                contentDescription = "Coil Sample",
                modifier = Modifier
                    .clickable(onClick = {})
                    .height(100.dp),
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(R.drawable.ic_loading),
                error = painterResource(R.drawable.ic_error),
                onSuccess = {
                    /* Handle success */
                },
                onLoading = { /* Show loading spinner */ },
            )
            Button(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    launcher.launch(
                        PickVisualMediaRequest(
                            //Supported Media Type
                            //.ImageAndVideo, .VideoOnly, .ImageOnly, .SingleMimeType
                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            ) {
                Text("Import Image")
            }
            if (photoUri != null) {
                val painter = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(context)
                        .data(data = photoUri)
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(onClick = {})
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .height(100.dp),
                )
            }
            LazyColumn {
                items(users.size) { pos ->
                    UserCard(users[pos])
                }
            }
        }
    }
}