package com.example.githubapp.app

import android.app.Application
import com.example.githubapp.data.network.Networking
import timber.log.Timber

class GithubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Networking.init(this)
        Timber.plant(Timber.DebugTree())
    }
}