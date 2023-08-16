package com.doctoror.splittor.platform

import android.app.Application
import com.doctoror.splittor.di.KoinStarter

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinStarter().start(this)
    }
}
