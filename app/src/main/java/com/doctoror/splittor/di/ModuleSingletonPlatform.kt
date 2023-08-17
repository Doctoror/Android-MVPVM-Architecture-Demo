package com.doctoror.splittor.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
class ModuleSingletonPlatform {

    @Provides
    fun contentResolver(@ApplicationContext context: Context) = context.contentResolver

    @Provides
    fun locale() = Locale.getDefault()

    @Provides
    @IoDispatcher
    fun dispatcherIo() = Dispatchers.IO
}