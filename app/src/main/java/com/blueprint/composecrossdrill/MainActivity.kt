package com.blueprint.composecrossdrill

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.blueprint.composecrossdrill.navigation.MainNavigation
import com.blueprint.composecrossdrill.ui.features.dashboard.screens.DashboardScreen
import com.blueprint.composecrossdrill.ui.theme.ComposeCrossDrillTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCrossDrillTheme {
                MainNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DevicePreview() {
    ComposeCrossDrillTheme {
        MainNavigation()
    }
}