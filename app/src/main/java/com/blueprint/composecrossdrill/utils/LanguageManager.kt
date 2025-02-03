package com.blueprint.composecrossdrill.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.*

class LanguageManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("language_pref", Context.MODE_PRIVATE)

    fun setLanguage(language: String) {
        sharedPreferences.edit().putString("language", language).apply()
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
        } else {
            config.locale = locale
        }
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun getLanguage(): String {
        return sharedPreferences.getString("language", "en") ?: "en"
    }
}

@Composable
fun getLanguagePreference(): String {
    val context = LocalContext.current
    val languageManager = LanguageManager(context)
    return languageManager.getLanguage()
}
