package com.doctoror.splittor.platform

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SCHEDULER_IO = "SCHEDULER_IO"
const val SCHEDULER_MAIN_THREAD = "SCHEDULER_MAIN_THREAD"

fun providePlatformModule() = module {

    factory { androidContext().contentResolver }

    single(named(SCHEDULER_IO)) { Schedulers.io() }

    single(named(SCHEDULER_MAIN_THREAD)) { AndroidSchedulers.mainThread() }
}
