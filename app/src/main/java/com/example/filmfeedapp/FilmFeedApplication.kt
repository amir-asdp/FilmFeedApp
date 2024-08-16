package com.example.filmfeedapp

import android.app.Application
import com.example.data.di.dataModule
import com.example.filmfeedapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FilmFeedApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FilmFeedApplication)
            modules(appModule, dataModule)
        }
    }
}