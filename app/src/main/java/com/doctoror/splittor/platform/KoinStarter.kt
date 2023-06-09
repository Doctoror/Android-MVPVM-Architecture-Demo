package com.doctoror.splittor.platform

import android.content.Context
import com.doctoror.splittor.data.contacts.provideContactsModule
import com.doctoror.splittor.data.groups.provideGroupsDataModule
import com.doctoror.splittor.data.numberformat.provideNumberFormatDataModule
import com.doctoror.splittor.presentation.addgroup.provideAddGroupModule
import com.doctoror.splittor.presentation.groupdetails.provideGroupDetailsModule
import com.doctoror.splittor.presentation.groupsoverview.provideGroupsOverviewPresentationModule
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
