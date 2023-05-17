package com.doctoror.splittor.data.numberformat

import com.doctoror.splittor.domain.numberformat.FormatAmountWithCurrencyUseCase
import org.koin.dsl.module
import java.util.Locale

fun provideNumberFormatDataModule() = module {

    factory<FormatAmountWithCurrencyUseCase> {
        FormatAmountWithCurrencyUseCaseImpl(Locale.getDefault())
    }
}
