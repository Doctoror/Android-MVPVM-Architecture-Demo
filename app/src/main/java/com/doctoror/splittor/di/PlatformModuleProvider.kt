package com.doctoror.splittor.di

import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.Locale

const val DISPATCHER_IO = "DISPATCHER_IO"

fun providePlatformModule() = module {

    factory { androidContext().contentResolver }

    factory { Locale.getDefault() }

    single(named(DISPATCHER_IO)) { Dispatchers.IO }
}
