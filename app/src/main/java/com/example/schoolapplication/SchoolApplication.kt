package com.example.schoolapplication

import android.app.Application
import com.example.schoolapplication.api.NetworkModule
import com.example.schoolapplication.repository.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SchoolApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@SchoolApplication)
            modules(listOf(NetworkModule, RepositoryModule))
        }
    }
}