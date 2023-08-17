package com.doctoror.splittor.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinStarter {

    fun start(androidContext: Context) = startKoin {
        androidContext(androidContext)
        modules(
            provideContactsModule(),
            provideGroupDetailsModule(),
            provideGroupsDataModule(),
            provideNumberFormatDataModule(),
            providePlatformModule()
        )
    }
}
