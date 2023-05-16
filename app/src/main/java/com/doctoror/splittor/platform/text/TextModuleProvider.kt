package com.doctoror.splittor.platform.text

import org.koin.dsl.module
import java.util.Locale

fun provideTextModule() = module {

    factory { AmountFormatter(Locale.US) }
}
