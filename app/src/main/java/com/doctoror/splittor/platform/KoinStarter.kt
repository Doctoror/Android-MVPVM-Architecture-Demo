package com.doctoror.splittor.platform

import android.content.Context
import com.doctoror.splittor.data.contacts.provideContactsModule
import com.doctoror.splittor.data.groups.provideGroupsDataModule
import com.doctoror.splittor.presentation.addgroup.provideAddGroupModule
import com.doctoror.splittor.presentation.groups.provideGroupsPresentationModule
import com.doctoror.splittor.presentation.text.provideTextModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinStarter {

    fun start(androidContext: Context) = startKoin {
        androidContext(androidContext)
        modules(
            provideAddGroupModule(),
            provideContactsModule(),
            provideGroupsDataModule(),
            provideGroupsPresentationModule(),
            providePlatformModule(),
            provideTextModule()
        )
    }
}
