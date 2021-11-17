package com.doctoror.splittor.platform

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinStarter().start(this)
    }
}
