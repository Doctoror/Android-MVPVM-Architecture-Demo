package com.doctoror.splittor.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.Locale

fun providePlatformModule() = module {

    factory { androidContext().contentResolver }

    factory { Locale.getDefault() }
}
