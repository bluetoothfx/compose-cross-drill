package com.blueprint.composecrossdrill

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.blueprint.composecrossdrill.navigation.MainNavigation
import com.blueprint.composecrossdrill.ui.theme.ComposeCrossDrillTheme
import com.blueprint.composecrossdrill.utils.LanguageManager

class MainActivity : ComponentActivity() {
    override fun attachBaseContext(newBase: Context) {
        val languageManager = LanguageManager(newBase)
        super.attachBaseContext(languageManager.updateLocale(newBase))
    }

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