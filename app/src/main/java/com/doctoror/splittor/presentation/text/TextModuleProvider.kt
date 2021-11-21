package com.doctoror.splittor.presentation.text

import org.koin.dsl.module
import java.util.*

fun provideTextModule() = module {

    factory { AmountFormatter(Locale.US) }
}
