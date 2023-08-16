package com.doctoror.splittor.di

import com.doctoror.splittor.data.groups.GroupsRepositoryFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun provideGroupsDataModule() = module {

    single { GroupsRepositoryFactory(androidContext()).newInstance() }
}
