package com.example.travelfeeldog.util

import android.app.Application
import com.example.travelfeeldog.di.dataSourceModule
import com.example.travelfeeldog.di.networkModule
import com.example.travelfeeldog.di.repositoryModule
import com.example.travelfeeldog.di.viewModelModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule, viewModelModule, repositoryModule, dataSourceModule)
        }

        setUpFirebaseApp()
        setUpTimber()

    }

    private fun setUpFirebaseApp(){
        FirebaseApp.initializeApp(this)
    }

    private fun setUpTimber(){
        Timber.plant(Timber.DebugTree())
    }
}