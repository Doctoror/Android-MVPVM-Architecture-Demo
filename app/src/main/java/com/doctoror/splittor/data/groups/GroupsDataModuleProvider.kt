package com.doctoror.splittor.data.groups

import androidx.room.Room
import com.doctoror.splittor.data.MainDatabase
import com.doctoror.splittor.domain.groups.GroupsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun provideGroupsDataModule() = module {

    single {
        Room
            .databaseBuilder(
                androidContext(),
                MainDatabase::class.java, "Main.db"
            )
            .build()
    }

    single<GroupsRepository> {
        GroupsRepositoryImpl(
            groupsDataSource = LocalGroupsDataSource(
                currentTimeProvider = { System.currentTimeMillis() },
                groupsDao = get<MainDatabase>().groupsDao()
            )
        )
    }
}
