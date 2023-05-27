package com.example.travelfeeldog.util

import android.app.Application
import com.example.travelfeeldog.di.dataSourceModule
import com.example.travelfeeldog.di.networkModule
import com.example.travelfeeldog.di.repositoryModule
import com.example.travelfeeldog.di.viewModelModule
import com.google.firebase.FirebaseApp
import com.naver.maps.map.BuildConfig
import com.naver.maps.map.NaverMapSdk
import org.koin.core.context.startKoin
import com.example.travelfeeldog.BuildConfig.NAVER_MAPS_CLIENT_ID
import timber.log.Timber

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule, viewModelModule, repositoryModule, dataSourceModule)
        }

        setUpFirebaseApp()
        setUpTimber()
        setUpMaps()
    }

    private fun setUpFirebaseApp() {
        FirebaseApp.initializeApp(this)
    }

    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setUpMaps() {
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient(
            NAVER_MAPS_CLIENT_ID
        )
    }
}