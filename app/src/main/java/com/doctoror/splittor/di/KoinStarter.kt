package com.doctoror.splittor.di

import android.content.Context
import com.doctoror.splittor.di.provideContactsModule
import com.doctoror.splittor.di.provideGroupsDataModule
import com.doctoror.splittor.di.provideNumberFormatDataModule
import com.doctoror.splittor.di.provideAddGroupModule
import com.doctoror.splittor.di.provideGroupDetailsModule
import com.doctoror.splittor.di.provideGroupsOverviewPresentationModule
import com.doctoror.splittor.di.providePlatformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinStarter {

    fun start(androidContext: Context) = startKoin {
        androidContext(androidContext)
        modules(
            provideAddGroupModule(),
            provideContactsModule(),
            provideGroupDetailsModule(),
            provideGroupsDataModule(),
            provideGroupsOverviewPresentationModule(),
            provideNumberFormatDataModule(),
            providePlatformModule()
        )
    }
}
