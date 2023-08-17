package com.doctoror.splittor.platform

import android.app.Application
import com.doctoror.splittor.di.KoinStarter
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinStarter().start(this)
    }
}
