package com.example.githubapp

import android.app.Application
import com.example.githubapp.di.appModule
import com.example.githubapp.di.dataModule
import com.example.githubapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            modules(listOf(appModule, dataModule, networkModule))
            androidContext(this@App)
        }

    }
}