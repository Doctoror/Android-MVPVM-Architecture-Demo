package com.doctoror.splittor.platform

import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DISPATCHER_IO = "DISPATCHER_IO"

fun providePlatformModule() = module {

    factory { androidContext().contentResolver }

    single(named(DISPATCHER_IO)) { Dispatchers.IO }
}
