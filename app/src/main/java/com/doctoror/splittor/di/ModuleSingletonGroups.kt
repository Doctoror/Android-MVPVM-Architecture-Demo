package com.doctoror.splittor.di

import android.content.Context
import com.doctoror.splittor.data.groups.GroupsRepositoryFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleSingletonGroups {

    @Provides
    @Singleton
    fun groupsRepository(@ApplicationContext context: Context) =
        GroupsRepositoryFactory(context).newInstance()
}