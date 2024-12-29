package com.blueprint.composecrossdrill

import android.app.Application
import com.blueprint.composecrossdrill.di.networkModule
import com.blueprint.composecrossdrill.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class StarterApp : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@StarterApp)
            // Load modules
            modules(networkModule, repositoryModule)
        }
    }
}