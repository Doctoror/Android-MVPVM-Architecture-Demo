package com.doctoror.splittor.platform

import android.content.Context
import com.doctoror.splittor.data.groups.provideGroupsDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinStarter {

    fun start(androidContext: Context) = startKoin {
        androidContext(androidContext)
        modules(provideGroupsDataModule())
    }
}
