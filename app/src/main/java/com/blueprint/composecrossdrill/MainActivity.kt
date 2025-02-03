package com.blueprint.composecrossdrill

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.blueprint.composecrossdrill.navigation.MainNavigation
import com.blueprint.composecrossdrill.ui.theme.ComposeCrossDrillTheme
import com.blueprint.composecrossdrill.utils.LanguageManager
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(updateLocale(newBase))
    }

    private fun updateLocale(context: Context): Context {
        // Get the language preference
        val languageManager = LanguageManager(context)
        val language = languageManager.getLanguage()

        // Apply the selected locale
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        return context.createConfigurationContext(config)
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